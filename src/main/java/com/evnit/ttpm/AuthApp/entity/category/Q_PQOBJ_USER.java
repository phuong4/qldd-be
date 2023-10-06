package com.evnit.ttpm.AuthApp.entity.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Table(name = "Q_PQOBJ_USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
@IdClass(Q_PQOBJ_USERKEY.class)

public class Q_PQOBJ_USER implements Serializable {
    private static long serialVersionUID = 1L;
    @Id
    @JsonProperty("ASSETID")
    @Column(name = "ASSETID")
    private String ASSETID;
    @Id
    @JsonProperty("USERID")
    @Column(name = "USERID")
    private String USERID;
    @JsonProperty("ISQL")
    @Column(name = "ISQL")
    private Boolean ISQL;
    @JsonProperty("ISLD")
    @Column(name = "ISLD")
    private Boolean ISLD;



}

