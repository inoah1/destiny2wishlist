package com.destiny2wishlist.ui.wishlist;

import com.destiny2wishlist.backend.entities.DestinyWeapon;
import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A form for editing a single product.
 */
@Slf4j
public class WeaponRollForm extends Div {

    private final VerticalLayout content;

    //TODO change this to select/combo box field
    private final ComboBox weaponName;
    private final ComboBox barrel;
    private final ComboBox magazine;
    private final ComboBox firstPerkName;
    private final ComboBox secondPerkName;
    private final TextField notes;
    //TODO add more fields
    private final Button delete;
    private final WeaponRollViewLogic viewLogic;
    private final Binder<DestinyWeaponRoll> binder;
    private Button save;
    private Button discard;
    private Button cancel;
    private DestinyWeaponRoll currentWeaponRoll;

    public WeaponRollForm(WeaponRollViewLogic viewLogic) {
        setClassName("product-form");

        content = new VerticalLayout();
        content.setSizeUndefined();
        content.addClassName("product-form-content");
        add(content);

        this.viewLogic = viewLogic;

        weaponName = new ComboBox("Weapon name");
        weaponName.setWidth("100%");
        weaponName.setRequired(true);
        weaponName.setId("weaponName");
        weaponName.setAllowCustomValue(false);
        content.add(weaponName);

        barrel = new ComboBox("Barrel");
        barrel.setWidth("100%");
        barrel.setId("barrel");
        barrel.setAllowCustomValue(false);
        content.add(barrel);

        magazine = new ComboBox("Magazine");
        magazine.setWidth("100%");
        magazine.setId("magazine");
        magazine.setAllowCustomValue(false);
        content.add(magazine);

        firstPerkName = new ComboBox("First perk");
        firstPerkName.setWidth("100%");
        firstPerkName.setId("firstPerk");
        firstPerkName.setAllowCustomValue(false);
        content.add(firstPerkName);

        secondPerkName = new ComboBox("Second perk");
        secondPerkName.setWidth("100%");
        secondPerkName.setId("secondPerk");
        secondPerkName.setAllowCustomValue(false);
        content.add(secondPerkName);

        notes = new TextField("Notes");
        notes.setWidth("100%");
        notes.setId("notes");
        content.add(notes);

        binder = new BeanValidationBinder<>(DestinyWeaponRoll.class);
        binder.bindInstanceFields(this);

        // enable/disable save button while editing
        binder.addStatusChangeListener(event -> {
            final boolean isValid = !event.hasValidationErrors();
            final boolean hasChanges = binder.hasChanges();
            save.setEnabled(hasChanges && isValid);
            discard.setEnabled(hasChanges);
        });

        save = new Button("Save");
        save.setWidth("100%");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(event -> {
            if (currentWeaponRoll != null && binder.writeBeanIfValid(currentWeaponRoll)) {
                viewLogic.saveWeaponRoll(currentWeaponRoll);
                log.info("Saving weapon roll " + currentWeaponRoll);
            }
        });
        save.addClickShortcut(Key.KEY_S, KeyModifier.CONTROL);

        discard = new Button("Discard changes");
        discard.setWidth("100%");
        discard.addClickListener(event -> viewLogic.editWeaponRoll(currentWeaponRoll));

        cancel = new Button("Cancel");
        cancel.setWidth("100%");
        cancel.addClickListener(event -> viewLogic.cancelWeaponRoll());
        cancel.addClickShortcut(Key.ESCAPE);
        getElement().addEventListener("keydown", event -> viewLogic.cancelWeaponRoll()).setFilter("event.key == 'Escape'");

        delete = new Button("Delete");
        delete.setWidth("100%");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
        delete.addClickListener(event -> {
            if (currentWeaponRoll != null) {
                viewLogic.deleteWeaponRoll(currentWeaponRoll);
            }
        });

        content.add(save, discard, delete, cancel);
    }

    public void setWeapons(Collection<DestinyWeapon> destinyWeaponCollection) {
        if (destinyWeaponCollection != null) {
            List<String> destinyWeaponNameList = destinyWeaponCollection.stream().map(DestinyWeapon::getName).collect(Collectors.toList());
            weaponName.setItems(destinyWeaponNameList);
        }
    }

    public void editWeaponRoll(DestinyWeaponRoll weaponRoll) {
        if (weaponRoll == null) {
            weaponRoll = new DestinyWeaponRoll();
        }
        delete.setVisible(!weaponRoll.isNewRoll());
        currentWeaponRoll = weaponRoll;
        binder.readBean(weaponRoll);
    }
}
