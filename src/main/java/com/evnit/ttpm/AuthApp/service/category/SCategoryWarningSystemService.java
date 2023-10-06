package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.category.Plan.SearchPlanList;
import com.evnit.ttpm.AuthApp.model.category.WarningSystem.SearchWarningSystemList;

public interface SCategoryWarningSystemService {

	ResponseData getAll(SearchWarningSystemList searchWarningSystemList);


}
//