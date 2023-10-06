package com.evnit.ttpm.AuthApp.entity.device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class View_HTDD {
    @Id
    @Column(name = "ID_DIEMDO")
    private String idDiemDo;
    @Column(name = "ID_CONGTO")
    private String idCongTo;
    @Column(name = "ASSETDESC_DIEMDO")
    private String assetDescDiemDo;
    @Column(name = "SERIALNUM_CONGTO")
    private String serialNumCongTo;
    @Column(name = "TU_TEXT")
    private String tuText;
    @Column(name = "TI_TEXT")
    private String tiText;
    @Column(name = "STATUS_DIEMDO")
    private String statusDiemDo;
    @Column(name = "ID_POWER")
    private String idPower;
    @Column(name = "ASSETDESC_POWER")
    private String assetDescPower;
    @Column(name = "IDS_BTI")
    private String idsBTI;
    @Column(name = "IDS_BTU")
    private String idsBTU;
    @Column(name = "IDS_CUON_TI")
    private String idsCuonTI;
    @Column(name = "IDS_CUON_TU")
    private String idsCuonTU;
    @Column(name = "ISSAVE")
    private Boolean isSave;
}
