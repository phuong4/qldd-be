package com.evnit.ttpm.AuthApp.entity.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "A_ASSET_LINK")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(AssetLinkId.class)
public class A_ASSET_LINK {
    @Id
    @Column(name = "ASSETID")
    private String assetId;
    @Id
    @Column(name = "ASSETID_LINK")
    private String assetIdLink;
    @Column(name = "DESCLINK")
    private String descLink;

    public A_ASSET_LINK(String assetId, String assetIdLink) {
        this.assetId = assetId;
        this.assetIdLink = assetIdLink;
    }
}
