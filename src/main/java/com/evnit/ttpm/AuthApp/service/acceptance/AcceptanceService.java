package com.evnit.ttpm.AuthApp.service.acceptance;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.acceptance.AcceptanceChangeDto;
import com.evnit.ttpm.AuthApp.model.acceptance.AcceptanceCrudDto;
import com.evnit.ttpm.AuthApp.model.acceptance.AcceptanceDetailDto;
import com.evnit.ttpm.AuthApp.model.acceptance.SearchAcceptanceList;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface AcceptanceService {
    ResponseData createAcceptance(AcceptanceCrudDto dto);

    ResponseData createOrUpdateAcceptDetail(AcceptanceDetailDto dto);

    ResponseData createOrUpdateAcceptChange(AcceptanceChangeDto dto);

    ResponseData getAcceptDetail(String id);

    ResponseData getAcceptChange(String id);

    ResponseData getListAcceptanceToTable(SearchAcceptanceList dto);

    ResponseData updateNQL(@CurrentUser CustomUserDetails user, String id, String ASSETID, Boolean stt);

    ResponseData updateLDP(@CurrentUser CustomUserDetails user, String id, String ASSETID, Boolean stt);

    ResponseData getFileNghiemThu(String id);

    ResponseData deleteAcceptance(String id);

    boolean checkAcceptance(String accredId, String assetId, Date startDate, Date endDate, String acceptForm, String typeAccept);

    ResponseData updateAcceptance(String id, AcceptanceCrudDto dto) throws Exception;


}
