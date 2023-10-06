package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.category.KDLDD.SearchKDLDDList;
import com.evnit.ttpm.AuthApp.model.category.WarningDevice.SearchWarningDeviceList;

public interface SCategoryKDLDDService {

	ResponseData getAll(SearchKDLDDList sarchKDLDDList);


}
//