package com.destiny2wishlist.ui.wishlist;

import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import com.destiny2wishlist.backend.services.DataProvider;
import com.vaadin.flow.component.UI;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * This class provides an interface for the logical operations between the CRUD
 * view, its parts like the weapon roll editor form and the data source, including
 * fetching and saving products.
 * <p>
 * Having this separate from the view makes it easier to test various parts of
 * the system separately, and to e.g. provide alternative views for the same
 * data.
 */
@Slf4j
public class WeaponRollViewLogic implements Serializable {

    private final WishlistView view;
    private final DataProvider dataProvider;

    public WeaponRollViewLogic(WishlistView view, DataProvider dataProvider) {
        this.view = view;
        this.dataProvider = dataProvider;
    }

    public void init() {
        editWeaponRoll(null);
    }

    public void cancelWeaponRoll() {
        setFragmentParameter("");
        view.clearSelection();
    }

    private void setFragmentParameter(String weaponRollId) {
        String fragmentParameter;
        if (weaponRollId == null || weaponRollId.isEmpty()) {
            fragmentParameter = "";
        } else {
            fragmentParameter = weaponRollId;
        }

        log.info("fragmentParameter = " + fragmentParameter);

        UI.getCurrent().navigate(WishlistView.class, fragmentParameter);
    }

    public void enter(String weaponRollId) {
        if (weaponRollId != null && !weaponRollId.isEmpty()) {
            if (weaponRollId.equals("new")) {
                newWeaponRoll();
            } else {
                // Ensure this is selected
                try {
                    final long id = Long.parseLong(weaponRollId);
                    final DestinyWeaponRoll weaponRoll = findWeaponRoll(id);
                    view.selectRow(weaponRoll);
                } catch (NumberFormatException e) {
                }
            }
        } else {
            view.showForm(false);
        }
    }

    private DestinyWeaponRoll findWeaponRoll(long weaponRollId) {
        return dataProvider.findWeaponRollById(weaponRollId);
    }

    public void saveWeaponRoll(DestinyWeaponRoll weaponRoll) {
        final boolean newRoll = weaponRoll.isNewRoll();
        view.clearSelection();
        view.updateWeaponRoll(weaponRoll);
        setFragmentParameter("");
        //TODO come up with a better notification message
        view.showSaveNotification(weaponRoll.getWeaponName() + (newRoll ? " new roll created" : " roll updated"));
    }

    public void deleteWeaponRoll(DestinyWeaponRoll weaponRoll) {
        view.clearSelection();
        view.removeWeaponRoll(weaponRoll);
        setFragmentParameter("");
        //TODO come up with a better notification message
        view.showSaveNotification(weaponRoll.getWeaponName() + " roll removed");
    }

    public void editWeaponRoll(DestinyWeaponRoll weaponRoll) {
        if (weaponRoll == null) {
            setFragmentParameter("");
        } else {
            setFragmentParameter(weaponRoll.getId() + "");
        }
        view.editWeaponRoll(weaponRoll);
    }

    public void newWeaponRoll() {
        view.clearSelection();
        setFragmentParameter("new");
        view.editWeaponRoll(new DestinyWeaponRoll());
    }

    public void rowSelected(DestinyWeaponRoll weaponRoll) {
        editWeaponRoll(weaponRoll);
    }
}
