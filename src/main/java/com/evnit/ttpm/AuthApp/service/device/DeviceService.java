package com.evnit.ttpm.AuthApp.service.device;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuon;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.device.common.DeviceCrudDto;
import com.evnit.ttpm.AuthApp.model.device.common.DeviceDto;
import com.evnit.ttpm.AuthApp.model.device.common.SearchHTDDListDto;
import com.evnit.ttpm.AuthApp.model.device.congto.CheckDeviceDto;
import com.evnit.ttpm.AuthApp.model.device.congto.CongToCrudDto;
import com.evnit.ttpm.AuthApp.model.device.congto.SearchCongToList;
import com.evnit.ttpm.AuthApp.model.device.ti.SearchTIList;
import com.evnit.ttpm.AuthApp.model.device.ti.SetTICreateDto;
import com.evnit.ttpm.AuthApp.model.device.tu.SearchTUList;
import com.evnit.ttpm.AuthApp.model.device.tu.SetTUCreateDto;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

public interface DeviceService {
    ResponseData getCongToKD(String transId, String strCongTo, String categoryId);

    ResponseData getListCongTo(SearchCongToList searchCongToList);

    ResponseData getDiemDoById(String id);

    ResponseData getCongToBySerialNum(String serialNum);

    ResponseData getTUBySerialNum(String serialNum);

    ResponseData getSetTUByIdAndIdsCuon(String assetId, List<String> cuonIds);

    ResponseData getSetTIByIdAndIdsCuon(String assetId, List<String> cuonIds);

    ResponseData getTIBySerialNum(String serialNum);

    ResponseData getListTU(SearchTUList searchDto);

    ResponseData getListTI(SearchTIList dto);

    ResponseData createDevice(DeviceCrudDto dto, String categoryId) throws Exception;

    ResponseData updateDevice(String id, DeviceCrudDto dto, String categoryId);

    ResponseData deleteDevice(String id, String categoryId);


    Boolean validateCongToSerialNum(String serialNum, String congToId);

    ResponseData getListTUChild(SearchTUList searchDto);

    ResponseData getListTIChild(SearchTIList searchDto);

    ResponseData getHTDDByDiemDoId(SearchHTDDListDto id);

    ResponseData deleteHTDDByDiemDoId(String idDiemDo);

    ResponseData createDevices(DeviceDto dto, CustomUserDetails user) throws Exception;

    ResponseData deleteRecordTemp(BigInteger id);

    ResponseData deleteDiemDo(String id) throws Exception;

    ResponseData deleteCongTo(String id) throws Exception;

    ResponseData deleteTU(String id) throws Exception;

    ResponseData deleteTI(String id) throws Exception;

    ResponseData updateCongTo(CustomUserDetails userDetails, CongToCrudDto dto) throws Exception;

    ViewCuon getCuonById(String id);

    ResponseData getSetTUById(String id);

    ResponseData getSetTIById(String id);

    ResponseData createOrUpdateTU(SetTUCreateDto setTuDto, CustomUserDetails userDetails, String diemDoId) throws Exception;

    @Transactional
    ResponseData updateTU(SetTUCreateDto setTuDto, CustomUserDetails userDetails, String diemDoId) throws Exception;

    @Transactional
    ResponseData updateTI(SetTICreateDto setTuDto, CustomUserDetails userDetails, String diemDoId) throws Exception;

    ResponseData createOrUpdateTI(SetTICreateDto setTuDto, CustomUserDetails userDetails, String diemDoId) throws Exception;

    ResponseData updateStatusDiemDo(String id, String status);

    ResponseData checkDevice(List<CheckDeviceDto> lstDevice);

    ResponseData getAllSetTU(SearchTUList dto);

    ResponseData getAllSetTI(SearchTIList dto);
    ResponseData getAssetDetail(String idDiemDo);

    ResponseData deleteCuonByIds(String idsCuon);
}
