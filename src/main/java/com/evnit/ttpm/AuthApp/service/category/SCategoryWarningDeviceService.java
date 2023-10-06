package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.category.WarningDevice.SearchWarningDeviceList;
import com.evnit.ttpm.AuthApp.model.category.WarningSystem.SearchWarningSystemList;

public interface SCategoryWarningDeviceService {

	ResponseData getAll(SearchWarningDeviceList searchWarningDeviceList);
	ResponseData GetThongTinKiemDinhTUTI(String accredId, String strThietBi);
	ResponseData GetThongTinKiemDinhTI(String accredId, String strThietBi);



}
//