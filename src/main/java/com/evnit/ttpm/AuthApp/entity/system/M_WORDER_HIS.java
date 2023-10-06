package com.evnit.ttpm.AuthApp.entity.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "M_WORDER_HIS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class M_WORDER_HIS {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1)
    @JsonProperty("ID")
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal ID;

    @Size(max = 36)
    @JsonProperty("WORDERID")
    @Column(name = "WORDERID")
    private String WORDERID;

    @JsonProperty("W_CONTENT")
    @Column(name = "W_CONTENT")
    private String W_CONTENT;

    @JsonProperty("W_CONTENT_EDIT")
    @Column(name = "W_CONTENT_EDIT")
    private String W_CONTENT_EDIT;

    @JsonProperty("VIEW_CONTENT")
    @Column(name = "VIEW_CONTENT")
    private String VIEW_CONTENT;

    @Size(max = 50)
    @JsonProperty("W_MANIPULATION")
    @Column(name = "W_MANIPULATION")
    private String W_MANIPULATION;

    @JsonProperty("DATE_MANIPULATION")
    @Column(name = "DATE_MANIPULATION")
    private Date DATE_MANIPULATION;

    @JsonProperty("USER_MDF_ID")
    @Column(name = "USER_MDF_ID")
    private String USER_MDF_ID;

    @Size(max = 36)
    @JsonProperty("TYPEWORDER")
    @Column(name = "TYPEWORDER")
    private String TYPEWORDER;
}


