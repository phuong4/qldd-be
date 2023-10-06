package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.Paramater.ParamaterCreateDto;
import com.evnit.ttpm.AuthApp.model.Paramater.SearchParamaterList;

public interface SCategoryParamaterService {

	ResponseData create(ParamaterCreateDto createDto);
	ResponseData update(int id,ParamaterCreateDto updateDto);

}
//