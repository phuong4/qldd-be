package com.evnit.ttpm.AuthApp.model.category.LicenseOperate;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LicenseOperateListDto {
	private Integer id;
    private String nmdId;
    private String name;
    private Date endDate;
    private Date startDate;
    private String endDateStr;
    private String startDateStr;
    private Boolean is_Delete;
	
    
}
