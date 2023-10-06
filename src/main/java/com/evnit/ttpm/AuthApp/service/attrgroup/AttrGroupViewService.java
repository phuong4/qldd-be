package com.evnit.ttpm.AuthApp.service.attrgroup;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import org.springframework.stereotype.Service;

@Service
public interface AttrGroupViewService {

	ResponseData getAttGroupView(String objID, String objTypeID, String orgidCurrent);

	Object runQueryList(String sql, Object[] para);

}
