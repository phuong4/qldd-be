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

@Table(name = "S_DONVISH")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class SCategoryDonViSH implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1)
    @JsonProperty("ID")
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 250)
    @JsonProperty("NAME")
    @Column(name = "NAME")
    private String name;
    @Size(max = 250)
    @JsonProperty("NAMESHORT")
    @Column(name = "NAMESHORT")
    private String nameShort;
    @Size(max = 1000000)
    @JsonProperty("TYPE")
    @Column(name = "TYPE")
    private String type;
    @Size(max = 1000000)
    @JsonProperty("PARENTID")
    @Column(name = "PARENTID")
    private String parentid;
    @Size(max = 250)
    @JsonProperty("NOTE")
    @Column(name = "NOTE")
    private String note;
    
    @JsonProperty("Is_Delete")
    @Column(name = "Is_Delete")
    private boolean is_Delete;

    @JsonProperty("IDHIS")
    @Column(name = "IDHIS")
    private String idHis;

	public boolean getIs_Delete() {
		return is_Delete;
	}

	public void setIs_Delete(boolean is_Delete) {
		this.is_Delete = is_Delete;
	}


}
