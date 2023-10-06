package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.SuCo.SearchSuCoList;
import com.evnit.ttpm.AuthApp.model.category.SuCo.SuCoCreateDto;

public interface SCategorySuCoService {

	ResponseData getAll(SearchSuCoList searchSuCoList);
	ResponseData getById(int id);
	ResponseData create(SuCoCreateDto createDto);
	ResponseData update(String DEALID,SuCoCreateDto updateDto);
	ResponseData delete(String id);
	ResponseData getFileKiemDinh(String KeyId);

    ResponseData updateNQL(@CurrentUser CustomUserDetails user, String PROBLEMID, String ASSETID, Boolean stt);

    ResponseData updateLDP(@CurrentUser CustomUserDetails user,String PROBLEMID,String ASSETID, Boolean stt);
}
//