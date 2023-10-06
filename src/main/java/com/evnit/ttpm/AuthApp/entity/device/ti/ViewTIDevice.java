package com.evnit.ttpm.AuthApp.entity.device.ti;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuon;
import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuonTI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "VIEWTI_DEVICE")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewTIDevice {
    @Id
    @Column(name = "IDTI")
    private String idTI;
    @Column(name = "TINAME")
    private String tiName;
    @Column(name = "CATEGORYID")
    private String categoryId;
    @Column(name = "IDDIEMDO")
    private String idDiemDo;
    @Column(name = "POWERSYSTEMID")
    private String powerSystemId;
    @Column(name = "POWERSYSTEMNAME")
    private String powerSystemName;
    @Column(name = "IDBOTI")
    private String idBoTI;
    @Column(name = "BOTINAME")
    private String boTIName;
    @Column(name = "TISERIALNUM")
    private String tiSerialNum;
    @Column(name = "P_MANUFACTURERID")
    private String pManufactureId;
    @Column(name = "MANUFACTURENAME")
    private String manufactureName;
    @Column(name = "DATEACCREDITATION")
    private String dateAccreditation;
    @Column(name = "KIEU_TI")
    private String kieuTI;
    @Column(name = "PDUYET_MAU")
    private String pDuyetMau;
    @Column(name = "PHA")
    private String pha;
    @Column(name = "STATUS_BTI")
    private String statusBTI;
    @Column(name = "STATUSTI")
    private String statusTI;
    @Column(name = "SETTYPESTR")
    private String setTypeStr;
    @Column(name = "ORGID")
    private String orgId;
    @Column(name = "SETTYPE")
    private Integer setType;
    @Column(name = "ASSETORD")
    private String assetORD;
    @Column(name = "ISDELETE")
    private Boolean isDelete;
    @OneToMany(mappedBy = "viewTIDevice", cascade = CascadeType.ALL)
    private List<ViewCuonTI> cuonAssets;
}
