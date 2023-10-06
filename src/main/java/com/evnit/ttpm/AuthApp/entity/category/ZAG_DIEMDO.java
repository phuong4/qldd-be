package com.evnit.ttpm.AuthApp.entity.category;

import com.evnit.ttpm.AuthApp.entity.common.SListAll;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "ZAG_DIEMDO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class ZAG_DIEMDO implements Serializable {
    private static final long serialVersionUID = 1L;
	@Id    @Size(max = 1000000)
    @JsonProperty("ATTRDATAID")
    @Size(max = 1000000)
    private String ATTRDATAID;
    @Size(max = 1000000)
    @JsonProperty("OBJTYPEID")
    @Column(name = "OBJTYPEID")
    private String OBJTYPEID;
    @Size(max = 1000000)
    @JsonProperty("OBJID")
    @Column(name = "OBJID")
    private String OBJID;
    @Size(min = 1)
    @JsonProperty("NTHU_TINH")
    @Column(name = "NTHU_TINH")
    private Date NTHU_TINH;
    @Size(max = 1000000)
    @JsonProperty("NTHU_TAI")
    @Column(name = "NTHU_TAI")
    private Date NTHU_TAI;
    @Size(max = 1000000)
    @JsonProperty("NTHU_TTSL")
    @Column(name = "NTHU_TTSL")
    private Date NTHU_TTSL;
    @Size(max = 1000000)
    @JsonProperty("TINH_CHAT1")
    @Column(name = "TINH_CHAT1")
    private String TINH_CHAT1;
    @Size(max = 1000000)
    @JsonProperty("DVI_GNHAN1")
    @Column(name = "DVI_GNHAN1")
    private String DVI_GNHAN1;
    @Size(max = 1000000)
    @JsonProperty("TINH_CHAT2")
    @Column(name = "TINH_CHAT2")
    private String TINH_CHAT2;
    @Size(max = 1000000)
    @JsonProperty("DVI_GNHAN2")
    @Column(name = "DVI_GNHAN2")
    private String DVI_GNHAN2;
    @Size(max = 1000000)
    @JsonProperty("DTAC_XNK")
    @Column(name = "DTAC_XNK")
    private String DTAC_XNK;
    @Size(max = 1000000)
    @JsonProperty("XNK")
    @Column(name = "XNK")
    private String XNK;
}
