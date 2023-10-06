package com.evnit.ttpm.AuthApp.entity.category;

import com.evnit.ttpm.AuthApp.model.category.SuCo.MapSuCoDiemDoKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Table(name = "PARAMATER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity

public class PARAMATER implements Serializable {
    private static long serialVersionUID = 1L;
    @Id
    //@JsonProperty("Id")
    @Column(name = "ID")
    private String id;
    @JsonProperty("Ua")
    @Column(name = "UA")
    private Float ua;
    @JsonProperty("Ub")
    @Column(name = "UB")
    private Float ub;
    @JsonProperty("Uc")
    @Column(name = "UC")
    private Float uc;
    @JsonProperty("Ia")
    @Column(name = "IA")
    private Float ia;
    @JsonProperty("Ib")
    @Column(name = "IB")
    private Float ib;
    @JsonProperty("Ic")
    @Column(name = "IC")
    private Float ic;
    @JsonProperty("Phia")
    @Column(name = "PHIA")
    private Float phia;
    @JsonProperty("Phib")
    @Column(name = "PHIB")
    private Float phib;
    @JsonProperty("Phic")
    @Column(name = "PHIC")
    private Float phic;
    @JsonProperty("TbaId")
    @Column(name = "TBAID")
    private String tbaId;
    @JsonProperty("DiemDoId")
    @Column(name = "DIEMDOID")
    private String diemDoId;
    @JsonProperty("EventId")
    @Column(name = "EVENTID")
    private String eventId;
    @JsonProperty("DateEvent")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "DATEEVENT")
    private String dateEvent;

}

