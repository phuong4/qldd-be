package com.evnit.ttpm.AuthApp.model.accreditation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KiemDinhListDto {
    private String id;
    private String kieu;
    private String ten;
    private String hinhThuc;
    private Integer hinhThucId;
    private Date batDau;
    private Date ketThuc;
    private String loai;
    private String chiTiet;
    private Integer soLuot;
    private Integer soLuong;
    private Integer dat;
    private Integer khongDat;
    private Integer thayThe;
    private String chiTietKetQuaThucHien;
    private Date thangKd;
    private String assetId;
    private String accerdId;
    private Integer trangThaiNMD;
    private Boolean ld_xnhan;
    private Boolean nql_xnhan;
}
