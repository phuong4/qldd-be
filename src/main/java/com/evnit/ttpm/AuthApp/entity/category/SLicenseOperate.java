package com.evnit.ttpm.AuthApp.entity.category;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "S_LICENSE_OPERATE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class SLicenseOperate {
	private static long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1)
    @JsonProperty("ID")
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JsonProperty("NAME")
    @Column(name = "NAME")
    private String name;
    
    @JsonProperty("STARTDATE")
    @Column(name = "STARTDATE")
    private Date startDate;
    
    @Size(max = 50)
    @JsonProperty("ENDDATE")
    @Column(name = "ENDDATE")
    private Date endDate;
    
    @Size(max = 100)
    @JsonProperty("NMDID")
    @Column(name = "NMDID")
    private String nmdId;
    
    @JsonProperty("IS_DELETE")
    @Column(name = "IS_DELETE")
    private Boolean is_Delete;
}
