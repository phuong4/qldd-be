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
public class AccreditationDetailDto {
    private Integer accChange;
    private String accredDetailId;
    private String pha;
    private String accredId;
    private String assetId;
    private Boolean accred_Result;
    private String assetDesc;
    private String assetId_Parent;
    private Boolean isDelete;
    private Boolean selected;
    private String serialNum;
    private List<FileData> lstFile;
    private List<FileData> lstFileDelete;
    private String User_cr_id;
    private AccreditationResultMeterDataDto lstKetQuaKiemDinhCT;
    private AccreditationResultTUDataDto lstKetQuaKiemDinhTU;
    private AccreditationResultTIDataDto lstKetQuaKiemDinhTI;
}
