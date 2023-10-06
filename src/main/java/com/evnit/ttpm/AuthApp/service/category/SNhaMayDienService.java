package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.NhaMayDienCrudDto;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.SearchNMD;


public interface SNhaMayDienService {
	
	ResponseData getAll();

    ResponseData getList(SearchNMD dto);

    ResponseData getById(String id);
	ResponseData create(NhaMayDienCrudDto createDto);
	ResponseData update(String id, NhaMayDienCrudDto updateDto);
	ResponseData delete(String id);
}
