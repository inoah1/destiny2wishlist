package com.destiny2wishlist.ui.wishlist;

import com.destiny2wishlist.backend.api.exception.ApiClientException;
import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import com.destiny2wishlist.backend.services.DataProviderService;
import com.destiny2wishlist.backend.services.DestinyService;
import com.destiny2wishlist.backend.services.WishlistService;
import com.destiny2wishlist.ui.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Route(value = "Wishlist", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Slf4j
public class WishlistView extends HorizontalLayout implements HasUrlParameter<String> {

    public static final String VIEW_NAME = "Wishlist";
    private final WeaponRollGrid grid;
    private final WeaponRollForm form;
    private final WeaponRollViewLogic viewLogic;
    private final DataProviderService dataProviderService;
    private final WeaponRollDataProvider weaponRollDataProvider;
    private TextField filter;
    private Button newWeaponRoll;
    private Upload uploadWishlist;
    private Button generateWishlist;
    private final DestinyService destinyService;
    private final WishlistService wishlistService;

    public WishlistView(DataProviderService dataProviderService, DestinyService destinyService, WishlistService wishlistService) {
        this.dataProviderService = dataProviderService;
        viewLogic = new WeaponRollViewLogic(this, dataProviderService);
        this.destinyService = destinyService;
        this.wishlistService = wishlistService;
        weaponRollDataProvider = new WeaponRollDataProvider(this.dataProviderService);

        // load data into database
        try {
            destinyService.loadDestinyManifest();

        } catch (ApiClientException e) {
            log.error("Error occurred loading Destiny Manifest");
        }

        setSizeFull();
        final VerticalLayout topLayout = createTopBar();

        grid = new WeaponRollGrid();
        //grid.setDataProvider(getDataProvider());
        grid.setDataProvider(weaponRollDataProvider);
        grid.asSingleSelect().addValueChangeListener(event -> viewLogic.rowSelected(event.getValue()));

        form = new WeaponRollForm(viewLogic);
        form.setWeapons(dataProviderService.getAllWeapons());

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

    private DataProvider<DestinyWeaponRoll, Void> getDataProvider() {
        return DataProvider.fromCallbacks(
                // First callback fetches items based on a query
                query -> {
                    // The index of the first item to load
                    int offset = query.getOffset();

                    // The number of items to load
                    int limit = query.getLimit();

                    List<DestinyWeaponRoll> weaponRollList = dataProviderService.fetchWeaponRolls(offset, limit);

                    return weaponRollList.stream();
                },
                // Second callback fetches the number of items for a query
                query -> dataProviderService.getWeaponRollCount()
        );
    }

    public VerticalLayout createTopBar() {
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

        MemoryBuffer buffer = new MemoryBuffer();
        uploadWishlist = new Upload(buffer);
        uploadWishlist.setDropLabel(new Label("Upload a .txt file containing dimwishlist"));
        uploadWishlist.setAcceptedFileTypes("text/plain");

        uploadWishlist.addSucceededListener(e -> {
            log.info("MimeType - " + buffer.getFileData().getMimeType());
            if (buffer.getInputStream() != null) {
                wishlistService.uploadWishlist(buffer.getInputStream());
            }
        });

        //FIXME let us know if upload failed. Add progress bar
        uploadWishlist.addFailedListener(e -> {
            log.error("Failed uploading file", e.getReason());
            Notification.show(e.getReason() == null ? "" : e.getReason().getLocalizedMessage());
        });

        generateWishlist = new Button("Generate DIM Wishlist");
        //TODO add click listener

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidthFull();
        topLayout.add(filter);
        topLayout.add(newWeaponRoll);
        topLayout.setVerticalComponentAlignment(Alignment.START, filter);
        topLayout.expand(filter);

        HorizontalLayout wishListButtonsLayout = new HorizontalLayout();
        wishListButtonsLayout.setWidthFull();
        wishListButtonsLayout.add(uploadWishlist);
        wishListButtonsLayout.add(generateWishlist);

        final VerticalLayout header = new VerticalLayout();
        header.setWidthFull();
        header.add(topLayout);
        header.add(wishListButtonsLayout);

        return header;
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
