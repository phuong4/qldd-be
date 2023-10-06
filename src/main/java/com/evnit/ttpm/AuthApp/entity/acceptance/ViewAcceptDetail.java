package com.evnit.ttpm.AuthApp.entity.acceptance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "VIEW_ACCEPTANCE_DETAIL")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewAcceptDetail {
    @Id
    @Column(name = "ACCEPTDETAILID")
    private String acceptDetailId;
    @Column(name = "ACCEPTID")
    private String acceptId;
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "STATUS_ACCEPT")
    private Boolean statusAccept;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "HTTTSL_ACCEPT")
    private Boolean hTTTSLAccept;
    @Column(name = "ID_DIEMDO")
    private String idDiemDo;
    @Column(name = "ID_CONGTO")
    private String idCongTo;
    @Column(name = "ASSETDESC_DIEMDO")
    private String assetDescDiemDo;
    @Column(name = "SERIALNUM_CONGTO")
    private String serialNumCongTo;
    @Column(name = "ID_POWER")
    private String idPower;
    @Column(name = "ASSETDESC_POWER")
    private String assetDescPower;
    @Column(name = "STATUS_DIEMDO")
    private String statusDiemDo;
    @Column(name = "IDS_BTI")
    private String idsBTI;
    @Column(name = "IDS_BTU")
    private String idsBTU;
    @Column(name = "IDS_CUON_TU")
    private String idsCuonTU;
    @Column(name = "IDS_CUON_TI")
    private String idsCuonTI;
    @Column(name = "TI_TEXT")
    private String tiText;
    @Column(name = "TU_TEXT")
    private String tuText;
     @Column(name = "ACTIONENDDATE")
    private Date actionEndDate;

    public Boolean gethTTTSLAccept() {
        return hTTTSLAccept;
    }

    public void sethTTTSLAccept(Boolean hTTTSLAccept) {
        this.hTTTSLAccept = hTTTSLAccept;
    }
}
