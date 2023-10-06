/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evnit.ttpm.AuthApp.entity.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "S_LIST_GROUP_ALL")
@XmlRootElement
public class SListGroupAll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @JsonProperty("GROUPLISTID")
    @Column(name = "GROUPLISTID")
    private String grouplistid;
    @Size(max = 250)
    @JsonProperty("GROUPLISTDESC")
    @Column(name = "GROUPLISTDESC")
    private String grouplistdesc;
    @Size(max = 25)
    @JsonProperty("GROUPLIST_PARENT")
    @Column(name = "GROUPLIST_PARENT")
    private String grouplistParent;
    @JsonProperty("GROUPLISTORD")
    @Column(name = "GROUPLISTORD")
    private Integer grouplistord;

    public SListGroupAll() {
    }

    public SListGroupAll(String grouplistid) {
        this.grouplistid = grouplistid;
    }

    public String getGrouplistid() {
        return grouplistid;
    }

    public void setGrouplistid(String grouplistid) {
        this.grouplistid = grouplistid;
    }

    public String getGrouplistdesc() {
        return grouplistdesc;
    }

    public void setGrouplistdesc(String grouplistdesc) {
        this.grouplistdesc = grouplistdesc;
    }

    public String getGrouplistParent() {
        return grouplistParent;
    }

    public void setGrouplistParent(String grouplistParent) {
        this.grouplistParent = grouplistParent;
    }

    public Integer getGrouplistord() {
        return grouplistord;
    }

    public void setGrouplistord(Integer grouplistord) {
        this.grouplistord = grouplistord;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grouplistid != null ? grouplistid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SListGroupAll)) {
            return false;
        }
        SListGroupAll other = (SListGroupAll) object;
        if ((this.grouplistid == null && other.grouplistid != null) || (this.grouplistid != null && !this.grouplistid.equals(other.grouplistid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SListGroupAll[ grouplistid=" + grouplistid + " ]";
    }
    
}
