package com.destiny2wishlist.ui.wishlist;

import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import com.destiny2wishlist.backend.repositories.DestinyWeaponRollRepository;
import com.vaadin.flow.component.UI;

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
public class WeaponRollViewLogic implements Serializable {

    private final WishlistView view;
    private final DestinyWeaponRollRepository weaponRollRepository;

    public WeaponRollViewLogic(WishlistView view, DestinyWeaponRollRepository weaponRollRepository) {
        this.view = view;
        this.weaponRollRepository = weaponRollRepository;
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
        return weaponRollRepository.findById(weaponRollId).get();
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
