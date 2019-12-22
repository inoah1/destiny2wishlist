package com.destiny2wishlist.backend.services;


import com.destiny2wishlist.backend.entities.DestinyWeapon;
import com.destiny2wishlist.backend.entities.DestinyWeaponRoll;
import com.destiny2wishlist.backend.entities.DestinyWeaponSocket;
import com.destiny2wishlist.backend.repositories.DestinyWeaponRepository;
import com.destiny2wishlist.backend.repositories.DestinyWeaponRollRepository;
import com.destiny2wishlist.backend.repositories.DestinyWeaponSocketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class WishlistService {

    private final DestinyWeaponRollRepository weaponRollRepository;
    private final DestinyWeaponRepository weaponRepository;
    private final DestinyWeaponSocketRepository weaponPerkRepository;

    public WishlistService(DestinyWeaponRollRepository weaponRollRepository, DestinyWeaponRepository weaponRepository, DestinyWeaponSocketRepository weaponPerkRepository) {
        this.weaponRollRepository = weaponRollRepository;
        this.weaponRepository = weaponRepository;
        this.weaponPerkRepository = weaponPerkRepository;
    }

    @SuppressWarnings("ConstantConditions")
    public List<String> uploadWishlist(String filePath) {
        List<String> errorLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            Set<DestinyWeaponRoll> savedRolls = new HashSet<>();
            while ((line = br.readLine()) != null) {
                if (line.contains("dimwishlist:")) {
                    Long weaponId = getWeaponId(line);
                    if (weaponId > 0) {
                        List<Long> perkIdList = getWeaponPerks(line);

                        Optional<DestinyWeapon> byId = weaponRepository.findById(weaponId);
                        if (byId.isPresent()) {
                            DestinyWeapon destinyWeapon = byId.get();

                            DestinyWeaponRoll destinyWeaponRoll = createDestinyWeaponRoll(destinyWeapon, perkIdList);

                            String notes = getWishlistNotes(line);
                            destinyWeaponRoll.setNotes(notes);

                            try {
                                if (!savedRolls.contains(destinyWeaponRoll)) {
                                    weaponRollRepository.save(destinyWeaponRoll);
                                    savedRolls.add(destinyWeaponRoll);
                                }
                            } catch (DataIntegrityViolationException e) {
                                // Do nothing
                            }
                        } else {
                            log.error("Weapon not found - " + line);
                            errorLines.add(line);
                        }
                    }
                    //TODO handle trash list items

                }
            }
            log.info("Number of weapon rolls saved " + savedRolls.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("Completed loading wishlist");

        return errorLines;
    }

    private DestinyWeaponRoll createDestinyWeaponRoll(DestinyWeapon destinyWeapon, List<Long> perkIdList) {
        DestinyWeaponRoll weaponRoll = new DestinyWeaponRoll();
        weaponRoll.setWeaponId(destinyWeapon.getHash());
        weaponRoll.setWeaponName(destinyWeapon.getName());

        if (perkIdList != null && !perkIdList.isEmpty()) {
            for (int i = 0; i < perkIdList.size(); i++) {
                Optional<DestinyWeaponSocket> byId;
                switch (i) {
                    case 0:
                        byId = weaponPerkRepository.findById(perkIdList.get(i));
                        if (byId.isPresent()) {
                            DestinyWeaponSocket weaponPerk = byId.get();
                            weaponRoll.setBarrelId(weaponPerk.getHash());
                            weaponRoll.setBarrel(weaponPerk.getName());
                        }
                        break;
                    case 1:
                        byId = weaponPerkRepository.findById(perkIdList.get(i));
                        if (byId.isPresent()) {
                            DestinyWeaponSocket weaponPerk = byId.get();
                            weaponRoll.setMagazineId(weaponPerk.getHash());
                            weaponRoll.setMagazine(weaponPerk.getName());
                        }
                        break;
                    case 2:
                        byId = weaponPerkRepository.findById(perkIdList.get(i));
                        if (byId.isPresent()) {
                            DestinyWeaponSocket weaponPerk = byId.get();
                            weaponRoll.setFirstPerkId(weaponPerk.getHash());
                            weaponRoll.setFirstPerkName(weaponPerk.getName());
                        }
                        break;
                    case 3:
                        byId = weaponPerkRepository.findById(perkIdList.get(i));
                        if (byId.isPresent()) {
                            DestinyWeaponSocket weaponPerk = byId.get();
                            weaponRoll.setSecondPerkId(weaponPerk.getHash());
                            weaponRoll.setSecondPerkName(weaponPerk.getName());
                        }
                        break;
                }
            }
        }

        return weaponRoll;
    }

    private Long getWeaponId(String line) {
        Pattern pattern = Pattern.compile("dimwishlist:item=([^&|#]*)");
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            return Long.parseLong(matcher.group(1).trim());
        }
        return 0L;
    }

    private List<Long> getWeaponPerks(String line) {
        List<Long> perkList = new ArrayList<>();
        Pattern pattern = Pattern.compile("perks=([^#]*)");
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            String perkStr = matcher.group(1);
            String[] perkStrArr = perkStr.split(",");
            for (String s : perkStrArr) {
                perkList.add(Long.parseLong(s.trim()));
            }
        }

        return perkList;
    }

    private String getWishlistNotes(String line) {
        String notes = null;
        Pattern pattern = Pattern.compile("#notes:(.+)*");
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            notes = matcher.group(1);
        }

        return notes;
    }
}
