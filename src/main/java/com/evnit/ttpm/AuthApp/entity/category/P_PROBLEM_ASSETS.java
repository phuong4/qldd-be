package com.evnit.ttpm.AuthApp.entity.category;

import com.evnit.ttpm.AuthApp.model.category.SuCo.MapSuCoDiemDoKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Table(name = "P_PROBLEM_ASSETS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
@IdClass(MapSuCoDiemDoKey.class)

public class P_PROBLEM_ASSETS implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "PROBLEMID")
    @Id
    private String PROBLEMID;
    @Size(max = 250)
    @JsonProperty("ASSETID")
    @Column(name = "ASSETID")
    @Id
    private String ASSETID;
    @JsonProperty("TYPE")
    @Column(name = "TYPE")
    private Integer TYPE;
    @JsonProperty("ISDELETE")
    @Column(name = "ISDELETE")
    private boolean ISDELETE;
}

