package com.evnit.ttpm.AuthApp.model.accreditation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccreditationResultTUDto {
    private String accredTUId;
    private String accredDetailId;
    private String ccx;
    private String dvi_tnd;
    private String tsb;
    private String dungLuong;
    private Double uf80;
    private Double ussg80;
    private Double uf100;
    private Double ussg100;
    private Double uf120;
    private Double ussg120;
    private Date ngay_cnhat;
    private String nguoi_cnhat;
    private String pha;
    private String soCheTao;
    private String cuonDauNoi;
    private String assetId;
}
