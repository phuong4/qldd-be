package com.evnit.ttpm.AuthApp.service.admin;

import com.evnit.ttpm.AuthApp.entity.admin.Function;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.S_Organization;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

	List<Function> getAllQ_Function();

	List<Function> getLstFuncByUserId(String userID, boolean bWithRole, boolean authNetworkType);

	List<S_Organization> getALLOrg();

	List<S_Organization> getALLOrgByParentID(String orgIDParent);

	ResponseData getALLOrgByUserID(String userid);

	S_Organization getOrgCurrent(String userid);
}
