package com.evnit.ttpm.AuthApp.entity.category;

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

@Table(name = "View_TinhTP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class ViewTinhTP implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1)
    @JsonProperty("ID")
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 50)
    @JsonProperty("NAME")
    @Column(name = "NAME")
    private String name;
    @Size(max = 100)
    @JsonProperty("DOMAIN")
    @Column(name = "DOMAIN")
    private int domain;
    @JsonProperty("ISDELETE")
    @Column(name = "ISDELETE")
    private boolean isdelete;
    @JsonProperty("MIEN")
    @Column(name = "MIEN")
    private String mien;
    @JsonProperty("GUID")
    @Column(name = "GUID")
    private String guid;



//    @Column(name = "IS_DELETE")
//    private Boolean isDelete;




}
