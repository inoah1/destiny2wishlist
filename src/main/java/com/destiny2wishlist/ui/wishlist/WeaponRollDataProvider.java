package com.destiny2wishlist.ui.wishlist;

import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import com.destiny2wishlist.backend.services.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.util.Locale;
import java.util.Objects;

/**
 * Utility class that encapsulates filtering and CRUD operations for
 * {@link DestinyWeaponRoll} entities.
 * <p>
 */
public class WeaponRollDataProvider extends ListDataProvider<DestinyWeaponRoll> {

    private final DataProvider dataProvider;
    // Text filter that can be changed separately.
    private String filterText = "";

    public WeaponRollDataProvider(DataProvider dataProvider) {
        super(dataProvider.getAllWeaponRolls());
        this.dataProvider = dataProvider;
    }

    /**
     * Store given weapon roll to the backing data service
     *
     * @param weaponRoll the updated or new weapon roll
     */
    public void save(DestinyWeaponRoll weaponRoll) {
        final boolean newWeaponRoll = weaponRoll.isNewRoll();

        dataProvider.updateWeaponRoll(weaponRoll);
        if (newWeaponRoll) {
            refreshAll();
        } else {
            refreshItem(weaponRoll);
        }

    }

    /**
     * Delete given weapon roll from the backing data service
     *
     * @param weaponRoll the weapon roll to be deleted
     */
    public void delete(DestinyWeaponRoll weaponRoll) {
        dataProvider.deleteWeaponRoll(weaponRoll.getId());
        refreshAll();
    }

    /**
     * Sets the filter to use this data provider and refreshes data
     * <p>
     * Filter is compared for weapon name
     *
     * @param filterText the text to filter by, never null
     */
    public void setFilter(String filterText) {
        Objects.requireNonNull(filterText, "Filter text cannot be null.");
        if (Objects.equals(this.filterText, filterText.trim())) {
            return;
        }
        this.filterText = filterText.trim();

        setFilter(weaponRoll -> passesFilter(weaponRoll.getWeaponName(), filterText));
    }

    @Override
    public Object getId(DestinyWeaponRoll item) {
        Objects.requireNonNull(item, "Cannot provide an id for a null weapon roll");

        return item.getId();
    }

    private boolean passesFilter(Object object, String filterText) {
        return object != null && object.toString().toLowerCase(Locale.ENGLISH).contains(filterText);
    }
}
