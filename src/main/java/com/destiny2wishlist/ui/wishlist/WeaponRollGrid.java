package com.destiny2wishlist.ui.wishlist;

import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;

public class WeaponRollGrid extends Grid<DestinyWeaponRoll> {

    public WeaponRollGrid() {
        setSizeFull();

        addColumn(DestinyWeaponRoll::getWeaponName).setHeader("Weapon name").setFlexGrow(20).setSortable(true).setKey("weaponname");

        addColumn(DestinyWeaponRoll::getFirstPerkName).setHeader("First perk").setFlexGrow(10).setKey("firstperk");

        addColumn(DestinyWeaponRoll::getSecondPerkName).setHeader("Second perk").setFlexGrow(10).setKey("second");

        addColumn(DestinyWeaponRoll::getThirdPerkName).setHeader("Third perk").setFlexGrow(10).setKey("thirdperk");

        addColumn(DestinyWeaponRoll::getFourthPerkName).setHeader("Fourth perk").setFlexGrow(10).setKey("fourthperk");

        // If the browser window size changes, check if all columns fit on screen
        // (e.g. switching from portrait to landscape mode)
        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> setColumnVisibility(e.getWidth()));
    }

    private void setColumnVisibility(int width) {
        if (width > 800) {
            getColumnByKey("weaponname").setVisible(true);
            getColumnByKey("firstperk").setVisible(true);
            getColumnByKey("secondperk").setVisible(true);
            getColumnByKey("thirdperk").setVisible(true);
            getColumnByKey("fourthperk").setVisible(true);
        } else {
            //TODO decide which columns to hide when browser window is smaller
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
