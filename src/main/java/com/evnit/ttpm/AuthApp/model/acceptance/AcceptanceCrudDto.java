package com.evnit.ttpm.AuthApp.model.acceptance;

import com.evnit.ttpm.AuthApp.entity.acceptance.AcceptanceChange;
import com.evnit.ttpm.AuthApp.entity.acceptance.AcceptanceDetail;
import com.evnit.ttpm.AuthApp.model.accreditation.FileData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcceptanceCrudDto {
    private String acceptId;
    private String acceptDesc;
    private String assetId;
    private Date actionStartDate;
    private Date actionEndDate;
    private String typeAccept;
    private String statusAccept;
    private String resultAccept;
    private String acceptForm;
    private String userCrId;
    private Date userCrDTime;
    private String userMdfId;
    private Date userMdfDTime;
    private Boolean nqlXNhan;
    private Boolean ldXNhan;
    private List<AcceptanceDetail> details;
    private List<AcceptanceChange> changes;
    private List<FileData> fileData;
    private List<FileData> lstFileDelete;
    private BigInteger transId;
}
