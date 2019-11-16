package com.destiny2wishlist.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="destiny_manifest")
@NoArgsConstructor
@AllArgsConstructor
public class DestinyManifest {

    @Id
    @Column(unique = true, nullable = false)
    private String version;

    @Lob
    private String json;

    @Column(name = "last_refresh_date")
    private LocalDate lastRefreshDate;

    @Column(unique = true, nullable = false)
    private boolean isLatest;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public DestinyManifest(String version, String json) {
        this.version = version;
        this.json = json;
        this.lastRefreshDate = LocalDate.now();
    }
}
