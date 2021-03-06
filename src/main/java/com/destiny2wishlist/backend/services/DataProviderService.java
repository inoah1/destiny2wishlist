package com.destiny2wishlist.backend.services;

import com.destiny2wishlist.backend.entities.DestinyWeapon;
import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import com.destiny2wishlist.backend.repositories.DestinyWeaponRepository;
import com.destiny2wishlist.backend.repositories.DestinyWeaponRollRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DataProviderService {

    private final DestinyWeaponRepository weaponRepository;
    private final DestinyWeaponRollRepository weaponRollRepository;

    private int weaponRollCount = 0;

    public DataProviderService(DestinyWeaponRepository weaponRepository, DestinyWeaponRollRepository weaponRollRepository) {
        this.weaponRepository = weaponRepository;
        this.weaponRollRepository = weaponRollRepository;
    }

    public DestinyWeaponRoll findWeaponRollById(long weaponRollId) {
        return weaponRollRepository.findById(weaponRollId).get();
    }

    public Collection<DestinyWeaponRoll> getAllWeaponRolls() {
        return Collections.unmodifiableList(StreamSupport.stream(weaponRollRepository.findAll().spliterator(), false).collect(Collectors.toList()));
    }

    public synchronized void updateWeaponRoll(DestinyWeaponRoll weaponRoll) {
        weaponRollRepository.save(weaponRoll);
    }

    public synchronized void deleteWeaponRoll(Long id) {
        Optional<DestinyWeaponRoll> byId = weaponRollRepository.findById(id);
        if (byId.isPresent()) {
            weaponRollRepository.delete(byId.get());
        }
    }

    public Collection<DestinyWeapon> getAllWeapons() {
        return Collections.unmodifiableList(StreamSupport.stream(weaponRepository.findAll().spliterator(), false).collect(Collectors.toList()));
    }

    public List<DestinyWeaponRoll> fetchWeaponRolls(int offset, int limit) {
        Page<DestinyWeaponRoll> weaponRolls = weaponRollRepository.findAll(PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "id")));
        weaponRollCount = weaponRolls.getSize();
        return weaponRolls.getContent();
    }

    public int getWeaponRollCount() {
        return weaponRollCount;
    }
}
