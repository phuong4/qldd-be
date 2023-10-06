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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Table(name = "M_ACCREDITATION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class M_ACCREDITATION {
    private static final long serialVersionUID = 1L;

    @Column(name = "ACCREDID")
    @Id
    private String ACCREDID;

    @Size(max = 150)
    @JsonProperty("ACCREDDESC")
    @Column(name = "ACCREDDESC")
    private String ACCREDDESC;

    @Size(max = 36)
    @JsonProperty("ASSETID")
    @Column(name = "ASSETID")
    private String ASSETID;

    @JsonProperty("ACCREDITATION_STARTDATE")
    @Column(name = "ACCREDITATION_STARTDATE")
    private Date ACCREDITATION_STARTDATE;

    @JsonProperty("ACCREDITATION_ENDDATE")
    @Column(name = "ACCREDITATION_ENDDATE")
    private Date ACCREDITATION_ENDDATE;

    @Size(max = 50)
    @JsonProperty("ACCREDITATION_TYPE")
    @Column(name = "ACCREDITATION_TYPE")
    private String ACCREDITATION_TYPE;

    @Size(max = 50)
    @JsonProperty("ACCREDITATION_RESULT")
    @Column(name = "ACCREDITATION_RESULT")
    private String ACCREDITATION_RESULT;

    @Size(max = 500)
    @JsonProperty("NOTE")
    @Column(name = "NOTE")
    private String NOTE;

    @Size(max = 50)
    @JsonProperty("USER_CR_ID")
    @Column(name = "USER_CR_ID")
    private String USER_CR_ID;

    @JsonProperty("USER_CR_DTIME")
    @Column(name = "USER_CR_DTIME")
    private Date USER_CR_DTIME;

    @Size(max = 50)
    @JsonProperty("USER_MDF_ID")
    @Column(name = "USER_MDF_ID")
    private String USER_MDF_ID;

    @JsonProperty("USER__MDF_DTIME")
    @Column(name = "USER__MDF_DTIME")
    private Date USER__MDF_DTIME;

    @JsonProperty("NQL_XNHAN")
    @Column(name = "NQL_XNHAN")
    private Boolean NQL_XNHAN;

    @JsonProperty("LD_XNHAN")
    @Column(name = "LD_XNHAN")
    private Boolean LD_XNHAN;
}
