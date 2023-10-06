package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.SearchDonViSH;
import com.evnit.ttpm.AuthApp.model.category.tbargl.SearchTBAGRL;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglCrudDto;

import java.util.UUID;

public interface SCategoryTbaRglService {

	ResponseData getAll();
	ResponseData getListTBA(SearchTBAGRL searchTBAGRL);
	ResponseData getById(String id);
	ResponseData create(TbaRglCrudDto createDto);
	ResponseData update(String id, TbaRglCrudDto updateDto);
	ResponseData delete(String id);

}
