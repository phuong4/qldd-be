package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.DonViSHCreateDto;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.SearchDonViSH;

public interface SCategoryDonViSHService {

	ResponseData getAll();
	ResponseData getListDonViSH(SearchDonViSH searchDonViSH);
	ResponseData getById(int id);
	ResponseData create(DonViSHCreateDto createDto);
	ResponseData update(int id,DonViSHCreateDto updateDto);
	ResponseData delete(int id);

}
//