package com.evnit.ttpm.AuthApp.entity.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@Table(name = "S_Pair_Forwarding_Units")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class SPairForwardingUnits implements Serializable {
	 private static long serialVersionUID = 1L;
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @JsonProperty("UNIT1")
    @Column(name = "UNIT1")
    private Integer unit1;
    
    
    @JsonProperty("DESCRIPTION")
    @Column(name = "DESCRIPTION",columnDefinition = "NVARCHAR(MAX)")
    private String description;
    
    @JsonProperty("UNIT2")
    @Column(name = "UNIT2")
    private Integer unit2;

	@JsonProperty("GUID")
	@Column(name = "GUID")
	private String guid;
    
    @JsonProperty("IS_DELETE")
    @Column(name = "IS_DELETE")
    private boolean is_Delete;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UNIT1",insertable = false,updatable = false)
    private SDeliveryUnit sDeliveryUnit;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UNIT2",insertable = false,updatable = false)
    private SDeliveryUnit sDeliveryUnit1;
    
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUnit1() {
		return unit1;
	}

	public void setUnit1(Integer unit1) {
		this.unit1 = unit1;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUnit2() {
		return unit2;
	}

	public void setUnit2(Integer unit2) {
		this.unit2 = unit2;
	}

	public boolean getIs_Delete() {
		return is_Delete;
	}

	public void setIs_Delete(boolean is_Delete) {
		this.is_Delete = is_Delete;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		SPairForwardingUnits.serialVersionUID = serialVersionUID;
	}
	public SDeliveryUnit getSDeliveryUnit() {
		return sDeliveryUnit;
	}
	public void setSDeliveryUnit(SDeliveryUnit sDeliveryUnit) {
		this.sDeliveryUnit = sDeliveryUnit;
	}
	public SDeliveryUnit getSDeliveryUnit1() {
		return sDeliveryUnit1;
	}
	public void setSDeliveryUnit1(SDeliveryUnit sDeliveryUnit1) {
		this.sDeliveryUnit1 = sDeliveryUnit1;
	}
	
    
}
