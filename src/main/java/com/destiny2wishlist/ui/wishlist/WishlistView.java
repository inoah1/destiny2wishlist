package com.destiny2wishlist.ui.wishlist;

import com.destiny2wishlist.backend.api.exception.ApiClientException;
import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import com.destiny2wishlist.backend.services.DataProvider;
import com.destiny2wishlist.backend.services.DestinyService;
import com.destiny2wishlist.backend.services.WishlistService;
import com.destiny2wishlist.ui.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import lombok.extern.slf4j.Slf4j;

@Route(value = "Wishlist", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Slf4j
public class WishlistView extends HorizontalLayout implements HasUrlParameter<String> {

    public static final String VIEW_NAME = "Wishlist";
    private final WeaponRollGrid grid;
    private final WeaponRollForm form;
    private final WeaponRollViewLogic viewLogic;
    private final DataProvider dataProvider;
    private final WeaponRollDataProvider weaponRollDataProvider;
    private TextField filter;
    private Button newWeaponRoll;
    private final DestinyService destinyService;
    private final WishlistService wishlistService;

    public WishlistView(DataProvider dataProvider, DestinyService destinyService, WishlistService wishlistService) {
        this.dataProvider = dataProvider;
        viewLogic = new WeaponRollViewLogic(this, dataProvider);
        this.destinyService = destinyService;
        this.wishlistService = wishlistService;
        weaponRollDataProvider = new WeaponRollDataProvider(this.dataProvider);

        // load data into database
        try {
            destinyService.loadDestinyManifest();

        } catch (ApiClientException e) {
            log.error("Error occurred loading Destiny Manifest");
        }

        setSizeFull();
        final HorizontalLayout topLayout = createTopBar();

        grid = new WeaponRollGrid();
        grid.setDataProvider(weaponRollDataProvider);
        grid.asSingleSelect().addValueChangeListener(event -> viewLogic.rowSelected(event.getValue()));

        form = new WeaponRollForm(viewLogic);
        form.setWeapons(dataProvider.getAllWeapons());
        //TODO when text fields changes to drop-downs on form, find a way to provide all possible weapons to form

        final VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(topLayout);
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setFlexGrow(0, topLayout);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);

        add(barAndGridLayout);
        add(form);

        viewLogic.init();

    }

    public HorizontalLayout createTopBar() {
        filter = new TextField();
        filter.setPlaceholder("Filter weapon name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> weaponRollDataProvider.setFilter(event.getValue()));
        filter.addFocusShortcut(Key.KEY_F, KeyModifier.CONTROL);

        newWeaponRoll = new Button("Add to Wishlist");
        newWeaponRoll.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        newWeaponRoll.setIcon(VaadinIcon.PLUS_CIRCLE.create());
        newWeaponRoll.addClickListener(click -> viewLogic.newWeaponRoll());
        // Using ALT+N because Ctrl+N will create new browser window which is unavoidable
        newWeaponRoll.addClickShortcut(Key.KEY_N, KeyModifier.ALT);

        final HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidthFull();
        topLayout.add(filter);
        topLayout.add(newWeaponRoll);
        topLayout.setVerticalComponentAlignment(Alignment.START, filter);
        topLayout.expand(filter);

        return topLayout;
    }

    public void showError(String msg) {
        Notification.show(msg);
    }

    public void showSaveNotification(String msg) {
        Notification.show(msg);
    }

    public void setNewWeaponRollEnabled(boolean enabled) {
        newWeaponRoll.setEnabled(enabled);
    }

    public void clearSelection() {
        grid.getSelectionModel().deselectAll();
    }

    public void selectRow(DestinyWeaponRoll row) {
        grid.getSelectionModel().select(row);
    }

    public DestinyWeaponRoll getSelectedRow() {
        return grid.getSelectedRow();
    }

    public void updateWeaponRoll(DestinyWeaponRoll weaponRoll) {
        weaponRollDataProvider.save(weaponRoll);
    }

    public void removeWeaponRoll(DestinyWeaponRoll weaponRoll) {
        weaponRollDataProvider.delete(weaponRoll);
    }

    public void editWeaponRoll(DestinyWeaponRoll weaponRoll) {
        showForm(weaponRoll != null);
        form.editWeaponRoll(weaponRoll);
    }

    public void showForm(boolean show) {
        form.setVisible(show);
        form.setEnabled(show);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String parameter) {
        viewLogic.enter(parameter);
    }
}
