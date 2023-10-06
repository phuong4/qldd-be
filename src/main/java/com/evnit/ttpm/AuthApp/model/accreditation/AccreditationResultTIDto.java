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
public class AccreditationResultTIDto {
    private String accredTIId;
    private String accredDetailId;
    private String dvi_tnd;
    private String ccx;
    private String tsb;
    private String dungLuong;
    private Double uf1;
    private Double ussg1;
    private Double uf5;
    private Double ussg5;
    private Double uf20;
    private Double ussg20;
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
