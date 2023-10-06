package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.SearchThoaThuanList;
import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.ThoaThuanCreateDto;

public interface SCategoryThoaThuanService {

	ResponseData getAll(SearchThoaThuanList searchThoaThuanList);
	ResponseData getById(int id);
	ResponseData create(ThoaThuanCreateDto createDto);
	ResponseData update(String DEALID,ThoaThuanCreateDto updateDto);
	ResponseData delete(String id);
	ResponseData getFileKiemDinh(String accredId);

	ResponseData updateNQL(@CurrentUser CustomUserDetails user, String id,String ASSETID, Boolean stt);

	ResponseData updateLDP(@CurrentUser CustomUserDetails user,String id, String ASSETID,Boolean stt);
}
//