package com.evnit.ttpm.AuthApp.entity.device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "VIEW_GET_ASSETDETAIL")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViewGetAssetDetail {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "ID_DIEMDO")
    private String idDiemDo;
    @Column(name = "ID_TU")
    private String idTU;
    @Column(name = "ID_BTU")
    private String idBTU;
    @Column(name = "ID_BTI")
    private String idBTI;
    @Column(name = "ID_TI")
    private String idTI;
    @Column(name = "ID_CUON_TU")
    private String idCuonTU;
    @Column(name = "ID_CUON_TI")
    private String idCuonTI;
}
