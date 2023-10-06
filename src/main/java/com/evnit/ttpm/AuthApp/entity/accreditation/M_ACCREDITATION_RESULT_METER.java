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
import java.util.Date;

@Table(name = "M_ACCREDITATION_RESULT_METER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class M_ACCREDITATION_RESULT_METER implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "MACCREDMETERTID")
    @Id
    private String MACCREDMETERTID;

    @Size(max = 36)
    @JsonProperty("ACCREDDETAILID")
    @Column(name = "ACCREDDETAILID")
    private String ACCREDDETAILID;

    @Size(max = 50)
    @JsonProperty("CCX")
    @Column(name = "CCX")
    private String CCX;

    @Size(max = 50)
    @JsonProperty("DVI_TND")
    @Column(name = "DVI_TND")
    private String DVI_TND;

    @Size(max = 50)
    @JsonProperty("DIENAP")
    @Column(name = "DIENAP")
    private String DIENAP;

    @Size(max = 50)
    @JsonProperty("TAI_IN")
    @Column(name = "TAI_IN")
    private String TAI_IN;

    @Size(max = 50)
    @JsonProperty("PHA")
    @Column(name = "PHA")
    private String PHA;

    @Size(max = 50)
    @JsonProperty("COSPHI")
    @Column(name = "COSPHI")
    private String COSPHI;

    @JsonProperty("WH_GIAO")
    @Column(name = "WH_GIAO")
    private Double WH_GIAO;

    @JsonProperty("WH_NHAN")
    @Column(name = "WH_NHAN")
    private Double WH_NHAN;

    @JsonProperty("VARH_GIAO")
    @Column(name = "VARH_GIAO")
    private Double VARH_GIAO;

    @JsonProperty("VARH_NHAN")
    @Column(name = "VARH_NHAN")
    private Double VARH_NHAN;

    @JsonProperty("NGAY_CNHAT")
    @Column(name = "NGAY_CNHAT")
    private Date NGAY_CNHAT;

    @Size(max = 50)
    @JsonProperty("NGUOI_CNHAT")
    @Column(name = "NGUOI_CNHAT")
    private String NGUOI_CNHAT;

    @JsonProperty("ROW_INDEX")
    @Column(name = "ROW_INDEX")
    private Integer ROW_INDEX;


    @JsonProperty("DeltaCt1")
    @Column(name = "DELTACT1")
    private Double DeltaCt1;

    @JsonProperty("DeltaCt2")
    @Column(name = "DELTACT2")
    private Double DeltaCt2;

    @JsonProperty("DeltaCt3")
    @Column(name = "DELTACT3")
    private Double DeltaCt3;

    @JsonProperty("DeltaCt4")
    @Column(name = "DELTACT4")
    private Double DeltaCt4;

    @JsonProperty("DeltaMau")
    @Column(name = "DELTAMAU")
    private Double DeltaMau;


}
