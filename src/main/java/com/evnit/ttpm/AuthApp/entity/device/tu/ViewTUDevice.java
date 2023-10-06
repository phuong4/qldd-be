package com.evnit.ttpm.AuthApp.entity.device.tu;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "VIEWTU_DEVICE")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewTUDevice {
    @Id
    @Column(name = "IDTU")
    private String idTU;
    @Column(name = "TUNAME")
    private String tuName;
    @Column(name = "CATEGORYID")
    private String categoryId;
    @Column(name = "IDDIEMDO")
    private String idDiemDo;
    @Column(name = "POWERSYSTEMID")
    private String powerSystemId;
    @Column(name = "ORGID")
    private String orgId;
    @Column(name = "POWERSYSTEMNAME")
    private String powerSystemName;
    @Column(name = "IDBOTU")
    private String idBoTU;
    @Column(name = "BOTUNAME")
    private String boTUName;
    @Column(name = "TUSERIALNUM")
    private String tuSerialNum;
    @Column(name = "P_MANUFACTURERID")
    private String pManufactureId;
    @Column(name = "MANUFACTURENAME")
    private String manufactureName;
    @Column(name = "DATEACCREDITATION")
    private String dateAccreditation;
    @Column(name = "KIEU_TU")
    private String kieuTU;
    @Column(name = "PDUYET_MAU")
    private String pDuyetMau;
    @Column(name = "PHA")
    private String pha;
    @Column(name = "STATUS_BTU")
    private String statusBTU;
    @Column(name = "STATUSTU")
    private String statusTU;
    @Column(name = "SETTYPESTR")
    private String setTypeStr;
    @Column(name = "SETTYPE")
    private Integer setType;
    @Column(name = "ASSETORD")
    private String assetORD;
    @Column(name = "ISDELETE")
    private Boolean isDelete;
    @OneToMany(mappedBy = "viewTuDevice", cascade = CascadeType.ALL)
    private List<ViewCuon> cuonAssets;
}
