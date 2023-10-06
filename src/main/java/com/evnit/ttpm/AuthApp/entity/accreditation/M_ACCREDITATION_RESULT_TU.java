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

@Table(name = "M_ACCREDITATION_RESULT_TU")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class M_ACCREDITATION_RESULT_TU implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "MACCREDTUID")
    @Id
    private String MACCREDTUID;

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

    @JsonProperty("DUNGLUONG")
    @Column(name = "DUNGLUONG")
    private String DUNGLUONG;

    @JsonProperty("UF80")
    @Column(name = "UF80")
    private Double UF80;

    @JsonProperty("USSG80")
    @Column(name = "USSG80")
    private Double USSG80;

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

    @JsonProperty("DeltaTu1")
    @Column(name = "DELTATU1")
    private Double DeltaTu1;

    @JsonProperty("DeltaTu2")
    @Column(name = "DELTATU2")
    private Double DeltaTu2;

    @JsonProperty("DeltaTu3")
    @Column(name = "DELTATU3")
    private Double DeltaTu3;

    @JsonProperty("DeltaTu4")
    @Column(name = "DELTATU4")
    private Double DeltaTu4;

    @JsonProperty("DeltaTu5")
    @Column(name = "DELTATU5")
    private Double DeltaTu5;

    @JsonProperty("DeltaTu6")
    @Column(name = "DELTATU6")
    private Double DeltaTu6;

    @JsonProperty("DeltaMau")
    @Column(name = "DELTAMAU")
    private Double DeltaMau;


    @JsonProperty("DeltaMauM")
    @Column(name = "DELTAMAUM")
    private Double DeltaMauM;

}
