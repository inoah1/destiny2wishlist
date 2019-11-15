package com.destiny2wishlist.ui;

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



    public MainView() {
        VaadinSession.getCurrent()
                .setErrorHandler((ErrorHandler) errorEvent -> {
                    log.error("Uncaught UI exception",
                            errorEvent.getThrowable());
                    Notification.show(
                            "We are sorry, but an internal error occurred");
                });

        setSizeFull();

        initSearchBar();

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
