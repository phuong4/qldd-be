package com.evnit.ttpm.AuthApp.service.system;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.common.SearchHisMountUnDto;
import com.evnit.ttpm.AuthApp.model.system.MWorderHisDto;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

public interface WonderHisService {
    ResponseData GetList(String worderId, String type);

    ResponseData GetListEvent(String Id, Integer eventType);

    ResponseData GetListMountUn(SearchHisMountUnDto dto);

    Boolean CreateHis(String worderid, Object obj, String userId, String Type, String manipulation);


    Boolean UpdateHis(String worderid, Object obj, Object obj1, List<String> ignoredFields, Map<String, String> variableNameMapping, String userId, String Type, String manipulation);

    Boolean UpdateHisTUTI(String worderid, Object obj, Object obj1, Object obj2, Object obj3, List<String> ignoredFields, Map<String, String> variableNameMapping, String userId, String Type, String manipulation);
}
