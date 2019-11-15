package com.destiny2wishlist.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="destiny_manifest")
@NoArgsConstructor
@AllArgsConstructor
public class DestinyManifest extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String version;

    @Lob
    private String json;

    @Column(name = "last_refresh_date")
    private LocalDate lastRefreshDate;

    @Column(unique = true, nullable = false)
    private boolean isLatest;

    public DestinyManifest(String version, String json) {
        this.version = version;
        this.json = json;
        this.lastRefreshDate = LocalDate.now();
    }
}
