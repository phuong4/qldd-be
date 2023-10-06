package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.category.Plan.SearchPlanList;

public interface SCategoryPlanService {

	ResponseData getAll(SearchPlanList searchPlanList);


}
//