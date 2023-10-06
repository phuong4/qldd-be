package com.evnit.ttpm.AuthApp.entity.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "S_CATEGORY_TBA_RGL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class SCategoryTbaRgl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @JsonProperty("ID")
    private String id;
    @Size(max = 50)
    @JsonProperty("VOLTAGE_LEVEL")
    @Column(name = "VOLTAGE_LEVEL")
    private String voltageLevel;
    @Size(max = 100)
    @JsonProperty("TBA_RGL_CODE")
    @Column(name = "TBA_RGL_CODE")
    private String tbaRglCode;
    @JsonProperty("TBA_RGL_NAME")
    @Column(name = "TBA_RGL_NAME",columnDefinition = "NVARCHAR(MAX)")
    private String tbaRglName;
    @JsonProperty("TBA_RGL_CITY")
    @Column(name = "TBA_RGL_CITY")
    private Integer tbaRglCity;
    @JsonProperty("TYPE")
    @Column(name = "TYPE")
    private Integer type;
    @JsonProperty("")
    @Column(name = "OWNERSHIP_UNIT")
    private Integer ownerShipUnit;
    @JsonProperty("MANAGE_UNIT")
    @Column(name = "MANAGE_UNIT")
    private Integer manageUnit;
    @JsonProperty("TBA_RGL_STATUS")
    @Column(name = "TBA_RGL_STATUS")
    private Integer tbaRglStatus;
    @JsonProperty("TBA_RGL_XNK")
    @Column(name = "TBA_RGL_XNK")
    private Boolean tbaRglXnk;
    @Column(name = "IS_DELETE")
    private Boolean isDelete;
    @Column(name = "DESCRIPTION",columnDefinition = "NVARCHAR(MAX)")
    private String Description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TBA_RGL_CITY",insertable = false,updatable = false)
    private SCategoryTinhTP sCategoryTinhTP;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNERSHIP_UNIT",insertable = false,updatable = false)
    private SCategoryDonViSH sCategoryDonViSHByOwnership;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGE_UNIT",insertable = false,updatable = false)
    private SCategoryDonViSH sCategoryDonViSHByManage;
}
