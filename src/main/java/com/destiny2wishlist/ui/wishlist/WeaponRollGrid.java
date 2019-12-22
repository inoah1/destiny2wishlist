package com.destiny2wishlist.ui.wishlist;

import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;

public class WeaponRollGrid extends Grid<DestinyWeaponRoll> {

    public WeaponRollGrid() {
        setSizeFull();

        addColumn(DestinyWeaponRoll::getWeaponName).setHeader("Weapon name").setFlexGrow(20).setSortable(true).setKey("name");

        addColumn(DestinyWeaponRoll::getBarrel).setHeader("Barrel").setFlexGrow(10).setKey("barrel");

        addColumn(DestinyWeaponRoll::getMagazine).setHeader("Magazine").setFlexGrow(10).setKey("magazine");

        addColumn(DestinyWeaponRoll::getFirstPerkName).setHeader("Perk").setFlexGrow(10).setKey("firstperk");

        addColumn(DestinyWeaponRoll::getSecondPerkName).setHeader("Perk").setFlexGrow(10).setKey("secondperk");

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
