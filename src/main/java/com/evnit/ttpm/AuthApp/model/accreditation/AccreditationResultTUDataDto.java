package com.evnit.ttpm.AuthApp.model.accreditation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccreditationResultTUDataDto {

    private String accredDetailId;
    private String dvi_tnd;
    private String nguoi_cnhat;
    private List<AccreditationResultTUDto> lstAccreditationResultTUTempDto;
}
