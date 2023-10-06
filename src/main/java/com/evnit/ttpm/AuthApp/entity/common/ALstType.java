package com.evnit.ttpm.AuthApp.entity.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "A_LST_TYPE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ALstType {
    @Id
    @Column(name = "TYPEID")
    private String typeId;
    @Column(name = "TYPEDESC")
    private String typeDesc;
    @Column(name = "TYPEORD")
    private Integer typeOrd;
    @Column(name = "ICON")
    private String icon;
    @Column(name = "ISVISIBLE")
    private Boolean isVisible;
    @Column(name = "TYPEID_PARENT")
    private String typeIdParent;
    public enum TYPENAME {
        NM, TBA, RGL
    }
}
