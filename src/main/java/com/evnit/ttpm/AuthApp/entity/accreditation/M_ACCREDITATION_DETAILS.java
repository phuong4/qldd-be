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
import java.io.Serializable;
import java.math.BigInteger;

@Table(name = "M_ACCREDITATION_DETAILS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class M_ACCREDITATION_DETAILS implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ACCREDDETAILID")
    @Id
    private String ACCREDDETAILID;

    @Size(max = 36)
    @JsonProperty("ACCREDID")
    @Column(name = "ACCREDID")
    private String ACCREDID;

    @Size(max = 36)
    @JsonProperty("ASSETID")
    @Column(name = "ASSETID")
    private String ASSETID;

    @JsonProperty("ACCRED_RESULT")
    @Column(name = "ACCRED_RESULT")
    private Boolean ACCRED_RESULT;


    @Size(max = 50)
    @JsonProperty("PHA")
    @Column(name = "PHA")
    private String PHA;
}
