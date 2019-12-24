package com.destiny2wishlist.ui.wishlist;

import com.destiny2wishlist.backend.entities.DestinyWeapon;
import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import com.destiny2wishlist.backend.entities.DestinyWeaponSocket;
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

import java.util.ArrayList;
import java.util.Collection;

/**
 * A form for editing a single product.
 */
@Slf4j
public class WeaponRollForm extends Div {

    private final VerticalLayout content;

    //TODO change this to select/combo box field
    private final ComboBox<DestinyWeapon> weapon;
    private final ComboBox<DestinyWeaponSocket> barrel;
    private final ComboBox<DestinyWeaponSocket> magazine;
    private final ComboBox<DestinyWeaponSocket> firstPerk;
    private final ComboBox<DestinyWeaponSocket> secondPerk;
    private final TextField notes;
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

        weapon = new ComboBox("Weapon name");
        weapon.setWidth("100%");
        weapon.setRequired(true);
        weapon.setId("weapon");
        weapon.setAllowCustomValue(false);
        weapon.setItemLabelGenerator(DestinyWeapon::getName);
        content.add(weapon);

        barrel = new ComboBox("Barrel");
        barrel.setWidth("100%");
        barrel.setId("barrel");
        barrel.setAllowCustomValue(false);
        barrel.setItemLabelGenerator(DestinyWeaponSocket::getName);
        content.add(barrel);

        magazine = new ComboBox("Magazine");
        magazine.setWidth("100%");
        magazine.setId("magazine");
        magazine.setAllowCustomValue(false);
        magazine.setItemLabelGenerator(DestinyWeaponSocket::getName);
        content.add(magazine);

        firstPerk = new ComboBox("First perk");
        firstPerk.setWidth("100%");
        firstPerk.setId("firstPerk");
        firstPerk.setAllowCustomValue(false);
        firstPerk.setItemLabelGenerator(DestinyWeaponSocket::getName);
        content.add(firstPerk);

        secondPerk = new ComboBox("Second perk");
        secondPerk.setWidth("100%");
        secondPerk.setId("secondPerk");
        secondPerk.setAllowCustomValue(false);
        secondPerk.setItemLabelGenerator(DestinyWeaponSocket::getName);
        content.add(secondPerk);

        notes = new TextField("Notes");
        notes.setWidth("100%");
        notes.setId("notes");
        content.add(notes);

        weapon.addValueChangeListener(event -> {
            DestinyWeapon eventWeapon = event.getValue();
            if (eventWeapon != null) {
                barrel.setItems(eventWeapon.getBarrel());
                magazine.setItems(eventWeapon.getMagazine());
                firstPerk.setItems(eventWeapon.getFirstPerk());
                secondPerk.setItems(eventWeapon.getSecondPerk());
            } else {
                // clear drop-down if no weapon selected
                barrel.setItems(new ArrayList<>());
                magazine.setItems(new ArrayList<>());
                firstPerk.setItems(new ArrayList<>());
                secondPerk.setItems(new ArrayList<>());
            }
        });

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
            //List<String> destinyWeaponNameList = destinyWeaponCollection.stream().map(DestinyWeapon::getName).collect(Collectors.toList());
            //weaponName.setItems(destinyWeaponNameList);
            weapon.setItems(destinyWeaponCollection);
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
