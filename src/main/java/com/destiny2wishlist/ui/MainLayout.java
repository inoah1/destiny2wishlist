package com.destiny2wishlist.ui;

import com.destiny2wishlist.ui.about.AboutView;
import com.destiny2wishlist.ui.wishlist.WishlistView;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Theme(value = Lumo.class)
@PWA(name = "Destiny 2 Wishlist generator", shortName = "D2Wishlist", iconPath = "icons/icon.png", backgroundColor = "#233348", themeColor = "#233348")
@CssImport("./styles/styles.css")
@CssImport(value = "./styles/menu-buttons.css", themeFor = "vaadin-button")
public class MainLayout extends AppLayout implements RouterLayout {

    public MainLayout() {

        // menu toggle
        final DrawerToggle drawerToggle = new DrawerToggle();
        drawerToggle.addClassName("menu-toggle");
        addToNavbar(drawerToggle);

        // image, logo
        final HorizontalLayout top = new HorizontalLayout();
        top.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        top.setClassName("menu-header");

        Icon icon = VaadinIcon.TABLE.create();
        final Label title = new Label("Destiny 2 Wishlist Generator");
        top.add(icon, title);
        top.add(title);
        addToNavbar(top);

        // Navigation items
        addToDrawer(createMenuLink(WishlistView.class, WishlistView.VIEW_NAME, VaadinIcon.LIST.create()));

        addToDrawer(createMenuLink(AboutView.class, AboutView.VIEW_NAME, VaadinIcon.INFO_CIRCLE.create()));

    }

    private RouterLink createMenuLink(Class<? extends Component> viewClass, String caption, Icon icon) {
        final RouterLink routerLink = new RouterLink(null, viewClass);
        routerLink.setClassName("menu-link");
        routerLink.add(icon);
        routerLink.add(new Span(caption));
        icon.setSize("24px");
        return routerLink;
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        //TODO add admin view if applicable
    }
}
