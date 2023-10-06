package com.evnit.ttpm.AuthApp.service.lookup;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.common.OptionSelect;
import com.evnit.ttpm.AuthApp.model.request.common.TreeSelect;

import java.util.Date;
import java.util.List;

public interface LookupService {
    ResponseData getCompaniesTree();
    ResponseData getCompaniesTreeLevel12();
    ResponseData getCompanies();
    ResponseData getCity();
    ResponseData getTBA();
    ResponseData getElectricFactory();
    ResponseData getTC();
    ResponseData getLHNMD();
    ResponseData getXNK();
    ResponseData getDTXNK();
    ResponseData getUser();
    ResponseData getRole(String UserId);
    ResponseData getTT();
    ResponseData getEvent(String TbaId,String DiemDoId);
    ResponseData getDateAnalysis(String TbaId,String DiemDoId,String EventId );
    ResponseData getPttsm(String TbaId, String DiemDoId, String EventId, String  Time );
    ResponseData getListType(String typeParent);
    ResponseData getDeliveryUnit();
    ResponseData getDeliveryUnitDD();

    ResponseData getListAccreditationByAssetId(String assetId);
}
