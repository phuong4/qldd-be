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

@Table(name = "S_Engine_Unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class SEngineUnit {
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
    
    @JsonProperty("Date_COD")
    @Column(name = "DATE_COD")
    private Date dateCOD;
    
    @JsonProperty("NMDID")
    @Column(name = "NMDID")
    private String nmdId;
    
    @JsonProperty("Cong_Suat_Min")
    @Column(name = "Cong_Suat_Min")
    private Integer cong_suat_min;
    
    @Size(max = 100)
    @JsonProperty("Cong_Suat_Max")
    @Column(name = "Cong_Suat_Max")
    private Integer cong_suat_max;
    
    @JsonProperty("Cong_Suat_Phan_Khang")
    @Column(name = "Cong_Suat_Phan_Khang")
    private Integer cong_suat_phan_khang;

    @JsonProperty("Time_Active")
    @Column(name = "Time_Active")
    private String timeActive;
    
    @JsonProperty("Speed_Of_Change_Reduce")
    @Column(name = "Speed_Of_Change_Reduce")
    private String speed_of_change_reduce;
    
    @JsonProperty("Speed_Of_Change_Increase")
    @Column(name = "Speed_Of_Change_Increase")
    private String speed_of_change_increase;
    
    @JsonProperty("Active")
    @Column(name = "Active")
    private Boolean active;
    

    @JsonProperty("Note")
    @Column(name = "Note")
    private String note;
    
    
    @JsonProperty("IS_DELETE")
    @Column(name = "IS_DELETE")
    private Boolean is_Delete;


}
