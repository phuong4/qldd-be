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
@Table(name = "S_LIST_ALL")
@XmlRootElement
public class SListAll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @JsonProperty("LISTID")
    @Column(name = "LISTID")
    private String listid;
    @Size(max = 25)
    @JsonProperty("GROUPLISTID")
    @Column(name = "GROUPLISTID")
    private String grouplistid;
    @Size(max = 250)
    @JsonProperty("LISTDESC")
    @Column(name = "LISTDESC")
    private String listdesc;
    @Size(max = 50)
    @JsonProperty("LISTCODE")
    @Column(name = "LISTCODE")
    private String listcode;
    @JsonProperty("LISTORD")
    @Column(name = "LISTORD")
    private Integer listord;

    public SListAll() {
    }

    public SListAll(String listid) {
        this.listid = listid;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public String getGrouplistid() {
        return grouplistid;
    }

    public void setGrouplistid(String grouplistid) {
        this.grouplistid = grouplistid;
    }

    public String getListdesc() {
        return listdesc;
    }

    public void setListdesc(String listdesc) {
        this.listdesc = listdesc;
    }

    public String getListcode() {
        return listcode;
    }

    public void setListcode(String listcode) {
        this.listcode = listcode;
    }

    public Integer getListord() {
        return listord;
    }

    public void setListord(Integer listord) {
        this.listord = listord;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listid != null ? listid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SListAll)) {
            return false;
        }
        SListAll other = (SListAll) object;
        if ((this.listid == null && other.listid != null) || (this.listid != null && !this.listid.equals(other.listid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SListAll[ listid=" + listid + " ]";
    }
    
}
