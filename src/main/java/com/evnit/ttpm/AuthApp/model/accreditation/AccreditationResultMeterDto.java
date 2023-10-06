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
public class AccreditationResultMeterDto {
    private String accredMeterId;
    private String accredDetailId;
    private String ccx;
    private String dvi_tnd;
    private String dienAp;
    private String taiIn;
    private String pha;
    private String cosPhi;
    private Double whGiao;
    private Double whNhan;
    private Double varhGiao;
    private Double varhNhan;
    private Date ngay_cnhat;
    private String nguoi_cnhat;
    private Integer index;
    private Double NUMBERERRORCT;
    private Double NUMBERERRORYESCT;

}
