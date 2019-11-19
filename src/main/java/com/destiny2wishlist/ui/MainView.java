package com.destiny2wishlist.ui;

import com.destiny2wishlist.backend.api.exception.ApiClientException;
import com.destiny2wishlist.backend.services.LoadManifestService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.ErrorHandler;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;
import lombok.extern.slf4j.Slf4j;

@Route
@PWA(name = "Destiny 2 Wishlist Generator", shortName = "D2 Wishlist")
@CssImport("./styles/styles.css")
@Slf4j
public class MainView extends VerticalLayout {

    private final LoadManifestService manifestService;

    public MainView(LoadManifestService manifestService) {
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

        try {
            manifestService.loadDestinyManifest();
        } catch (ApiClientException e) {
            log.error("Error getting Destiny manifest", e);
        }

    }

    private void initSearchBar() {
        ComboBox searchComboBox = new ComboBox("Select your weapon");
        searchComboBox.setPlaceholder("No weapon selected");

        // Disallow null selections
        searchComboBox.setPreventInvalidInput(true);
        // Disallow custom values
        searchComboBox.setAllowCustomValue(false);

        // Set full width
        searchComboBox.setWidthFull();

        add(searchComboBox);
    }
}
