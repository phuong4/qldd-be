package com.evnit.ttpm.AuthApp.entity.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Table(name = "VIEW_SPAIRFORWARDINGUNIT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class ViewSPairForwardingUnits implements Serializable {
	 private static long serialVersionUID = 1L;
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    
    @JsonProperty("UNIT1")
    @Column(name = "UNIT1")
    private Integer unit1;
    
    
    @JsonProperty("DESCRIPTION")
    @Column(name = "DESCRIPTION",columnDefinition = "NVARCHAR(MAX)")
    private String description;
    
    @JsonProperty("UNIT2")
    @Column(name = "UNIT2")
    private Integer unit2;
    
    @JsonProperty("IS_DELETE")
    @Column(name = "IS_DELETE")
    private boolean IS_DELETE;

	@JsonProperty("Unit1Name")
	@Column(name = "Unit1Name")
	private String unit1Name;
	@JsonProperty("Unit2Name")
	@Column(name = "Unit2Name")
	private String unit2Name;
    @JsonProperty("GUID")
    @Column(name = "GUID")
    private String guid;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		ViewSPairForwardingUnits.serialVersionUID = serialVersionUID;
	}

}
