package com.evnit.ttpm.AuthApp.entity.accreditation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;

@Table(name = "VIEW_ACCREDITATION_DEVICE")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewAccreditationDevice {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "ACCREDID")
    private String accredId;
    @Size(max = 150)
    @Column(name = "ACCREDDESC")
    private String accredDesc;
    @Size(max = 36)
    @Column(name = "ASSETID")
    private String assetId;
    @Column(name = "ACCREDITATION_STARTDATE")
    private Date accreditationStartDate;
    @Column(name = "ACCREDITATION_ENDDATE")
    private Date accreditationEndDate;
    @Size(max = 50)
    @Column(name = "ACCREDITATION_TYPE")
    private String accreditationType;
    @Size(max = 50)
    @Column(name = "ACCREDITATION_RESULT")
    private String accreditationResult;
    @Size(max = 500)
    @Column(name = "NOTE")
    private String note;
    @Size(max = 50)
    @Column(name = "USER_CR_ID")
    private String userCRId;
    @Column(name = "USER_CR_DTIME")
    private Date userCRDTime;
    @Size(max = 50)
    @Column(name = "USER_MDF_ID")
    private String userMDFId;
    @Column(name = "USER__MDF_DTIME")
    private Date userMDFDTime;
    @Column(name = "NQL_XNHAN")
    private Boolean nqlXNhan;
    @Column(name = "LD_XNHAN")
    private Boolean ldXNhan;
    @Column(name = "ACCREDDETAILID")
    private String accredDetailId;
    @Size(max = 36)
    @Column(name = "ASSETID_DEVICE")
    private String assetIdDevice;
    @Column(name = "ACCRED_RESULT")
    private Boolean accredResult;
    @Size(max = 50)
    @Column(name = "PHA")
    private String pha;
    @Column(name = "DEVICE_NAME")
    private String deviceName;
    @Column(name = "DEVICE_TYPE")
    private String deviceType;
}
