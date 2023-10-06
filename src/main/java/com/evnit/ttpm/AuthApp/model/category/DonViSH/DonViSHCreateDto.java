package com.evnit.ttpm.AuthApp.model.category.DonViSH;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonViSHCreateDto {
    private Integer id;
    private String name;
    private String nameShort;
    private String type;
    private String parentId;
    private String note;
    private String idHis;
    private String userId;
    private String parent;
    private String level;
    private boolean is_Delete;
	public boolean getIs_Delete() {
		return is_Delete;
	}
	public void setIs_Delete(boolean is_Delete) {
		this.is_Delete = is_Delete;
	}

  
	
	
    
}
