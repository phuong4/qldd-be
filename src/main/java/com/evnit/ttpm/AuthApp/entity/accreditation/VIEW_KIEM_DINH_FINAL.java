package com.evnit.ttpm.AuthApp.entity.accreditation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Table(name = "VIEW_KIEM_DINH_FINAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class VIEW_KIEM_DINH_FINAL {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("id")
    @Column(name = "ID")
    private String id;

    @JsonProperty("ld_xnhan")
    @Column(name = "LD_XNHAN")
    private Boolean ld_xnhan;

    @JsonProperty("nql_xnhan")
    @Column(name = "NQL_XNHAN")
    private Boolean nql_xnhan;

    @JsonProperty("kieu")
    @Column(name = "KIEU")
    private String kieu;

    @JsonProperty("ten")
    @Column(name = "TEN")
    private String ten;

    @JsonProperty("hinhThuc")
    @Column(name = "HINHTHUC")
    private String hinhThuc;

    @JsonProperty("hinhThucId")
    @Column(name = "HINHTHUCID")
    private String hinhThucId;

    @JsonProperty("batDau")
    @Column(name = "BATDAU")
    private Date batDau;

    @JsonProperty("thangKd")
    @Column(name = "THANGKD")
    private Date thangKd;

    @JsonProperty("ketThuc")
    @Column(name = "KETTHUC")
    private Date ketThuc;

    @JsonProperty("loai")
    @Column(name = "LOAI")
    private String loai;

    @JsonProperty("chiTiet")
    @Column(name = "CHITIET")
    private String chiTiet;

    @JsonProperty("soLuot")
    @Column(name = "SOLUOT")
    private Integer soLuot;

    @JsonProperty("soLuong")
    @Column(name = "SOLUONG")
    private Integer soLuong;

    @JsonProperty("dat")
    @Column(name = "DAT")
    private Integer dat;

    @JsonProperty("khongDat")
    @Column(name = "KHONGDAT")
    private Integer khongDat;

    @JsonProperty("thayThe")
    @Column(name = "THAYTHE")
    private Integer thayThe;

    @JsonProperty("chiTietKetQuaThucHien")
    @Column(name = "CHITIETKETQUATHUCHIEN")
    private String chiTietKetQuaThucHien;

    @JsonProperty("assetId")
    @Column(name = "ASSETID")
    private String assetId;

    @JsonProperty("accerdId")
    @Column(name = "ACCREDID")
    private String accerdId;

    @JsonProperty("trangThaiNMD")
    @Column(name = "TRANGTHAINMD")
    private Integer trangThaiNMD;

    @JsonProperty("CATEGORYID")
    @Column(name = "CATEGORYID")
    private String categoryId;
}
