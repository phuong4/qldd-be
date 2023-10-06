package com.evnit.ttpm.AuthApp.entity.category;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "S_Delivery_Unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class SDeliveryUnit implements Serializable {
	 private static long serialVersionUID = 1L;
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @JsonProperty("NAME")
    @Column(name = "NAME")
    private String name;
    
    
    @JsonProperty("DESCRIPTION")
    @Column(name = "DESCRIPTION",columnDefinition = "NVARCHAR(MAX)")
    private String description;
    
    @JsonProperty("XNK")
    @Column(name = "XNK")
    private boolean xnk;
    
    @JsonProperty("IS_DELETE")
    @Column(name = "IS_DELETE")
    private boolean is_Delete;

    @JsonProperty("GUID")
    @Column(name = "GUID")
    private String guid;

    public boolean getIs_Delete() {
        return is_Delete;
    }

    public void setIs_Delete(boolean is_Delete) {
        this.is_Delete = is_Delete;
    }
}
