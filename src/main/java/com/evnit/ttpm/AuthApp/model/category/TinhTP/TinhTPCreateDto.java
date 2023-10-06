package com.evnit.ttpm.AuthApp.model.category.TinhTP;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TinhTPCreateDto {
    private Integer id;
    private Integer domain;
    private String name;
    private boolean isdelete;
	private String userId;
	private String guid;
	private String mien;

  

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDomain() {
		return domain;
	}
	public void setdomain(Integer domain) {
		this.domain = domain;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsDelete() {
		return isdelete;
	}
	public void setIsDelete(boolean isdelete) {
		this.isdelete = isdelete;
	}
	
    
}
