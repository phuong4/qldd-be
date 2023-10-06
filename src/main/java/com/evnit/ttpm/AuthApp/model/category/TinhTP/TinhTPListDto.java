package com.evnit.ttpm.AuthApp.model.category.TinhTP;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TinhTPListDto {
	  private Integer id;
	    private Integer domain;
	    private String domainStr;
	    private String name;
	private String guid;
	private String mien;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDomain() {
		return domain;
	}
	public void setDomain(Integer domain) {
		this.domain = domain;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomainStr() {
		return domainStr;
	}
	public void setDomainStr(String domainStr) {
		this.domainStr = domainStr;
	}

	
}
