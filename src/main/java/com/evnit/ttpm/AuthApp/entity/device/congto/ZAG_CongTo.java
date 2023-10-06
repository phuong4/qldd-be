package com.evnit.ttpm.AuthApp.entity.device.congto;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryDonViSH;
import com.evnit.ttpm.AuthApp.entity.common.SListAll;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ZAG_CONGTO")
@Data
public class ZAG_CongTo implements Serializable {
    @Id
    @Column(name = "ATTRDATAID")
    private String attrDataId;
    @Column(name = "OBJTYPEID")
    private String objTypeId;
    @Column(name = "OBJID")
    private String objId;
    @Column(name = "LOAI_CTO")
    private String loaiCto;
    @Column(name = "KIEU_CTO")
    private String kieuCto;
    @Column(name = "I")
    private String i;
    @Column(name = "U")
    private String u;
    @Column(name = "CCX_Wh")
    private String ccxWh;
    @Column(name = "CCX_Varh")
    private String ccxVarh;
    @Column(name = "TSB_TU")
    private String tsbTU;
    @Column(name = "TSB_TI")
    private String tsbTI;
    @Column(name = "HSN")
    private String hsn;
    @Column(name = "LTRINH")
    private String lapTrinh;
    @Column(name = "CHI_NMD")
    private String chiNMD;
    @Column(name = "TINH_CHAT")
    private String tinhChat;

}
