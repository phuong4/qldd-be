package com.evnit.ttpm.AuthApp.service.accreditation;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.accreditation.*;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface AccreditationService {
    ResponseData getThietBiKD(String transId, String categoryId, String strThietBi, String nhaMayDienId);

    ResponseData GetThongTinKiemDinhTUTI(String accredId, String strThietBi);

    ResponseData GetThongTinKiemDinhTI(String accredId, String strThietBi);

    ResponseData getAccreditationDetails(String transId, String type, String accredId);

    ResponseData getResultMeter(String accredDetailId, String assetId);

    ResponseData createAccreditation(AccreditationDetailDto dto);

    ResponseData createAccreditationResultMeter(AccreditationResultMeterDataDto dto);

    ResponseData createAccreditationResultTU(AccreditationResultTUDataDto dto);

    ResponseData createAccreditationResultTI(AccreditationResultTIDataDto dto);

    ResponseData CreateAccreditation(AccreditationDto dto) throws Exception;

    ResponseData CreateAccreditationDetail(AccreditationDetailDto dto, String type) throws Exception;

    ResponseData getDanhSachThongTinKiemDinh(SearchKiemDinh dto);
    boolean checkAccreditation(String accredId, String assetId, Date startDate, Date endDate,String accredtationType);

//    ResponseData deleteAccreditationDetailMeter(String id);
//
//    ResponseData deleteAccreditationDetailTU(String id);
//
//    ResponseData deleteAccreditationDetailTI(String id);

    ResponseData deleteAccreditation(String id);

    ResponseData UpdateAccreditation(String id, AccreditationDto dto) throws Exception;


    ResponseData getFileKiemDinh(String accredId);

    ResponseData updateOrAddListAccreditationDetail(List<AccreditationDetailDto> dto, String AccreditationID, String type) throws Exception;

    ResponseData updateNQL(@CurrentUser CustomUserDetails user, String id, String ASSETID, Boolean stt);

    ResponseData updateLDP(@CurrentUser CustomUserDetails user, String id, String ASSETID, Boolean stt);

    ResponseData UpdateAccreditationDetail(AccreditationDetailDto dto, String type) throws Exception;

    ResponseData deleteAccreditationDetail(List<AccreditationDetailDto> dto, String type,  String id) throws Exception;
}