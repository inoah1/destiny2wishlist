package com.destiny2wishlist.ui;

import com.destiny2wishlist.backend.entities.DestinyWeapon;
import com.destiny2wishlist.backend.services.DestinyManifestService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.ErrorHandler;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Route
@PWA(name = "Destiny 2 Wishlist Generator", shortName = "D2 Wishlist")
@CssImport("./styles/styles.css")
@Slf4j
public class MainView extends VerticalLayout {

    private final DestinyManifestService manifestService;

    public MainView(DestinyManifestService manifestService) {
        VaadinSession.getCurrent()
                .setErrorHandler((ErrorHandler) errorEvent -> {
                    log.error("Uncaught UI exception",
                            errorEvent.getThrowable());
                    Notification.show(
                            "We are sorry, but an internal error occurred");
                });

        this.manifestService = manifestService;

        setSizeFull();

        loadDestinyManifest();
        initSearchBar();

    }

    private void loadDestinyManifest() {
        /*try {
            manifestService.loadDestinyManifest();
        } catch (ApiClientException e) {
            log.error("Error getting Destiny manifest", e);
        }*/
    }

    private void initSearchBar() {
        ComboBox<DestinyWeapon> searchComboBox = new ComboBox<>("Select your weapon");
        searchComboBox.setPlaceholder("No weapon selected");

        // Disallow null selections
        searchComboBox.setPreventInvalidInput(true);
        // Disallow custom values
        searchComboBox.setAllowCustomValue(false);
        // Set full width
        searchComboBox.setWidth("70%");
        searchComboBox.setClearButtonVisible(true);
        searchComboBox.setAutofocus(true);

        //Populate with data
        List<DestinyWeapon> allWeapons = manifestService.getAllWeapons();
        ListDataProvider<DestinyWeapon> destinyWeaponListDataProvider = DataProvider.ofCollection(allWeapons);
        destinyWeaponListDataProvider.setSortOrder(DestinyWeapon::getName, SortDirection.ASCENDING);
        searchComboBox.setDataProvider(destinyWeaponListDataProvider);
        searchComboBox.setItemLabelGenerator(DestinyWeapon::getName);




        add(searchComboBox);
        setAlignItems(Alignment.CENTER);
    }
}
