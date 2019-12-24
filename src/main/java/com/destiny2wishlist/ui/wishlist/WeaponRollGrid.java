package com.destiny2wishlist.ui.wishlist;

import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.vaadin.klaudeta.PaginatedGrid;

public class WeaponRollGrid extends PaginatedGrid<DestinyWeaponRoll> {

    //TODO find a way to get this from property file
    private final String bungieRootUrl = "https://www.bungie.net";

    public WeaponRollGrid() {
        setSizeFull();

        addColumn(new ComponentRenderer<>(weaponRoll -> {
            Image icon = new Image(bungieRootUrl + weaponRoll.getWeapon().getIcon(), "");
            return icon;
        }));

        addColumn(DestinyWeaponRoll::getWeaponName).setHeader("Weapon name").setFlexGrow(10).setSortable(true).setKey("name");

        addColumn(DestinyWeaponRoll::getBarrelName).setHeader("Barrel").setFlexGrow(10).setKey("barrel");

        addColumn(DestinyWeaponRoll::getMagazineName).setHeader("Magazine").setFlexGrow(10).setKey("magazine");

        addColumn(DestinyWeaponRoll::getFirstPerkName).setHeader("Perk").setFlexGrow(10).setKey("firstperk");

        addColumn(DestinyWeaponRoll::getSecondPerkName).setHeader("Perk").setFlexGrow(10).setKey("secondperk");


        // Sets the max number of items to be rendered on the grid for each page
        //setPage(20);
        setPageSize(10);

        // Sets how many pages should be visible on the pagination before and/or after current selected page
        setPaginatorSize(5);

        // If the browser window size changes, check if all columns fit on screen
        // (e.g. switching from portrait to landscape mode)
        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> setColumnVisibility(e.getWidth()));
    }

    private void setColumnVisibility(int width) {
        if (width > 800) {
            getColumnByKey("name").setVisible(true);
            getColumnByKey("barrel").setVisible(true);
            getColumnByKey("magazine").setVisible(true);
            getColumnByKey("firstperk").setVisible(true);
            getColumnByKey("secondperk").setVisible(true);
        } else {
            getColumnByKey("name").setVisible(true);
            getColumnByKey("barrel").setVisible(false);
            getColumnByKey("magazine").setVisible(false);
            getColumnByKey("firstperk").setVisible(true);
            getColumnByKey("secondperk").setVisible(true);
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        // fetch browser width
        UI.getCurrent().getInternals().setExtendedClientDetails(null);
        UI.getCurrent().getPage().retrieveExtendedClientDetails(e -> {
            setColumnVisibility(e.getBodyClientWidth());
        });
    }

    public DestinyWeaponRoll getSelectedRow() {
        return asSingleSelect().getValue();
    }

    public void refresh(DestinyWeaponRoll weaponRoll) {
        getDataCommunicator().refresh(weaponRoll);
    }
}
