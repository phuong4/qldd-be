package com.evnit.ttpm.AuthApp.entity.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "A_ASSET_HIS")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class A_ASSET_HIS {
    @Id
    @Column(name = "AHISID")
    private String aHisId;
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "ASSET_CONTENT")
    private String assetContent;
    @Column(name = "ASSET_CONTENT_EDIT")
    private String assetContentEdit;
    @Column(name = "ASSET_MANIPULATION")
    private String assetManipulation;
    @Column(name = "DATE_MANIPULATION")
    private String dateManipulation;
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
}
