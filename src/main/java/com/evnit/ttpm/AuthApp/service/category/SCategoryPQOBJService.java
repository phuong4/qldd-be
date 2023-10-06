package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.PQOBJ.PQOBJCreateDto;

public interface SCategoryPQOBJService {

	ResponseData create(PQOBJCreateDto createDto);
	ResponseData update(int id,PQOBJCreateDto updateDto);

}
//