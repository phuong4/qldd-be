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

@Table(name = "M_ACCREDITATION_RESULT_TI")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class M_ACCREDITATION_RESULT_TI implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "MACCREDTIID")
    @Id
    private String MACCREDTIID;

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
    @JsonProperty("TISO")
    @Column(name = "TISO")
    private String TISO;

    @JsonProperty("TAI")
    @Column(name = "TAI")
    private String TAI;

    @JsonProperty("UF1")
    @Column(name = "UF1")
    private Double UF1;

    @JsonProperty("USSG1")
    @Column(name = "USSG1")
    private Double USSG1;

    @JsonProperty("UF5")
    @Column(name = "UF5")
    private Double UF5;

    @JsonProperty("USSG5")
    @Column(name = "USSG5")
    private Double USSG5;

    @JsonProperty("UF20")
    @Column(name = "UF20")
    private Double UF20;

    @JsonProperty("USSG20")
    @Column(name = "USSG20")
    private Double USSG20;

    @JsonProperty("UF100")
    @Column(name = "UF100")
    private Double UF100;

    @JsonProperty("USSG100")
    @Column(name = "USSG100")
    private Double USSG100;

    @JsonProperty("UF120")
    @Column(name = "UF120")
    private Double UF120;

    @JsonProperty("USSG120")
    @Column(name = "USSG120")
    private Double USSG120;

    @JsonProperty("NGAY_CNHAT")
    @Column(name = "NGAY_CNHAT")
    private Date NGAY_CNHAT;

    @Size(max = 50)
    @JsonProperty("NGUOI_CNHAT")
    @Column(name = "NGUOI_CNHAT")
    private String NGUOI_CNHAT;


    @Size(max = 36)
    @JsonProperty("PHA")
    @Column(name = "PHA")
    private String PHA;

    @Size(max = 50)
    @JsonProperty("SERIALNUM")
    @Column(name = "SERIALNUM")
    private String SERIALNUM;

    @Size(max = 50)
    @JsonProperty("CUON_DNOI")
    @Column(name = "CUON_DNOI")
    private String CUON_DNOI;


    @JsonProperty("DeltaTi1")
    @Column(name = "DELTATI1")
    private Double DeltaTi1;

    @JsonProperty("DeltaTi2")
    @Column(name = "DELTATI2")
    private Double DeltaTi2;

    @JsonProperty("DeltaTi3")
    @Column(name = "DELTATI3")
    private Double DeltaTi3;

    @JsonProperty("DeltaTi4")
    @Column(name = "DELTATI4")
    private Double DeltaTi4;

    @JsonProperty("DeltaTi5")
    @Column(name = "DELTATI5")
    private Double DeltaTi5;

    @JsonProperty("DeltaTi6")
    @Column(name = "DELTATI6")
    private Double DeltaTi6;

    @JsonProperty("DeltaTi7")
    @Column(name = "DELTATI7")
    private Double DeltaTi7;

    @JsonProperty("DeltaTi8")
    @Column(name = "DELTATI8")
    private Double DeltaTi8;

    @JsonProperty("DeltaTi9")
    @Column(name = "DELTATI9")
    private Double DeltaTi9;

    @JsonProperty("DeltaTi10")
    @Column(name = "DELTATI10")
    private Double DeltaTi10;

    @JsonProperty("DeltaMauF1")
    @Column(name = "DELTAMAUF1")
    private Double DeltaMauF1;

    @JsonProperty("DeltaMauF5")
    @Column(name = "DELTAMAUF5")
    private Double DeltaMauF5;

    @JsonProperty("DeltaMauF20")
    @Column(name = "DELTAMAUF20")
    private Double DeltaMauF20;

    @JsonProperty("DeltaMauF100")
    @Column(name = "DELTAMAUF100")
    private Double DeltaMauF100;

    @JsonProperty("DeltaMauF120")
    @Column(name = "DELTAMAUF120")
    private Double DeltaMauF120;

    @JsonProperty("DeltaMauSG1")
    @Column(name = "DELTAMAUSG1")
    private Double DeltaMauSG1;

    @JsonProperty("DeltaMauSG5")
    @Column(name = "DELTAMAUSG5")
    private Double DeltaMauSG5;

    @JsonProperty("DeltaMauSG20")
    @Column(name = "DELTAMAUSG20")
    private Double DeltaMauSG20;

    @JsonProperty("DeltaMauSG100")
    @Column(name = "DELTAMAUSG100")
    private Double DeltaMauSG100;

    @JsonProperty("DeltaMauSG120")
    @Column(name = "DELTAMAUSG120")
    private Double DeltaMauSG120;







}
