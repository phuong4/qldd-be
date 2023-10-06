package com.evnit.ttpm.AuthApp.controller.device;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
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
import com.evnit.ttpm.AuthApp.service.device.DeviceService;
import com.evnit.ttpm.AuthApp.service.device.DeviceTIService;
import com.evnit.ttpm.AuthApp.service.device.DeviceTUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    DeviceTUService deviceTUService;
    @Autowired
    DeviceTIService deviceTIService;

    @GetMapping("htdd")
    public ResponseEntity<?> getHTDDByDiemDoId(SearchHTDDListDto dto) {
        return ResponseEntity.ok(deviceService.getHTDDByDiemDoId(dto));
    }

    @DeleteMapping("htdd/{idDiemDo}")
    public ResponseEntity<?> deleteHTDDByDiemDoId(@PathVariable String idDiemDo) {
        return ResponseEntity.ok(deviceService.deleteHTDDByDiemDoId(idDiemDo));
    }

    @PostMapping()
    public ResponseEntity<?> CreateDevices(@CurrentUser CustomUserDetails userDetails, @RequestBody DeviceDto dto) throws Exception {
        return ResponseEntity.ok(deviceService.createDevices(dto, userDetails));
    }

    @GetMapping("/cong-to")
    public ResponseEntity<?> GetCongTo(SearchCongToList congToList) {
        return ResponseEntity.ok(deviceService.getListCongTo(congToList));
    }

    //lấy danh sách công tơ đã gán điểm đo để kiểm định
    @GetMapping("/cong-to-kd/{transId}/{categoryId}")
    public ResponseEntity<?> GetCongToKD(@PathVariable("transId") String transId, String strCongTo, @PathVariable("categoryId") String categoryId) {
        return ResponseEntity.ok(deviceService.getCongToKD(transId, strCongTo, categoryId));
    }

    @GetMapping("/diem-do/{id}")
    public ResponseEntity<?> getDiemDoById(@PathVariable("id") String id) {
        return ResponseEntity.ok(deviceService.getDiemDoById(id));
    }

    @GetMapping("/cong-to/{serialNum}")
    public ResponseEntity<?> GetCongTo(@PathVariable("serialNum") String serialNum) {
        return ResponseEntity.ok(deviceService.getCongToBySerialNum(serialNum));
    }

    @PostMapping("/cong-to")
    public ResponseEntity<?> CreateCongTo(@CurrentUser CustomUserDetails userDetails, @RequestBody DeviceCrudDto dto) throws Exception {
        dto.setUserCRId(userDetails.getUserid());
        return ResponseEntity.ok(deviceService.createDevice(dto, A_ASSET.CategoryId.CONGTO.toString()));
    }

    @PutMapping("/cong-to/{id}")
    public ResponseEntity<?> UpdateCongTo(@PathVariable("id") String id, @CurrentUser CustomUserDetails userDetails, @RequestBody DeviceCrudDto dto) {
        dto.setUserMDFId(userDetails.getUserid());
        return ResponseEntity.ok(deviceService.updateDevice(id, dto, A_ASSET.CategoryId.CONGTO.toString()));
    }

    @DeleteMapping("/cong-to/{id}")
    public ResponseEntity<?> DeleteCongTo(@PathVariable("id") String id, @CurrentUser CustomUserDetails userDetails) {
        return ResponseEntity.ok(deviceService.deleteDevice(id, A_ASSET.CategoryId.CONGTO.toString()));
    }

    @DeleteMapping("/tu-delete/{id}")
    public ResponseEntity<?> DeleteTU(@PathVariable("id") String id, @CurrentUser CustomUserDetails userDetails) throws Exception {
        return ResponseEntity.ok(deviceService.deleteTU(id));
    }

    @DeleteMapping("/ti-delete/{id}")
    public ResponseEntity<?> DeleteTI(@PathVariable("id") String id, @CurrentUser CustomUserDetails userDetails) throws Exception {
        return ResponseEntity.ok(deviceService.deleteTI(id));
    }

    @GetMapping("/tu")
    public ResponseEntity<?> GetTU(SearchTUList searchDto) {
        return ResponseEntity.ok(deviceService.getListTU(searchDto));
    }

    @GetMapping("/ti")
    public ResponseEntity<?> GetTI(SearchTIList dto) {
        return ResponseEntity.ok(deviceService.getListTI(dto));
    }

    @GetMapping("/tu/{serialNum}")
    public ResponseEntity<?> GetTuBySerialNum(@PathVariable("serialNum") String serialNum) {
        return ResponseEntity.ok(deviceService.getTUBySerialNum(serialNum));
    }

    @GetMapping("/ti/{serialNum}")
    public ResponseEntity<?> GetTiBySerialNum(@PathVariable("serialNum") String serialNum) {
        return ResponseEntity.ok(deviceService.getTIBySerialNum(serialNum));
    }

    @GetMapping("/set-tu")
    public ResponseEntity<?> GetSetTUByIdAndIdsCuon(SearchTUList searchTUList) {
        return ResponseEntity.ok(deviceService.getSetTUByIdAndIdsCuon(searchTUList.getSetId(), searchTUList.getCuonIds()));
    }

    @GetMapping("/set-tu/{id}")
    public ResponseEntity<?> getSetTUById(@PathVariable String id) {
        return ResponseEntity.ok(deviceService.getSetTUById(id));
    }

    @GetMapping("/set-ti/{id}")
    public ResponseEntity<?> getSetTIById(@PathVariable String id) {
        return ResponseEntity.ok(deviceService.getSetTIById(id));
    }

    @GetMapping("/set-ti")
    public ResponseEntity<?> GetSetTIById(SearchTIList searchTIList) {
        return ResponseEntity.ok(deviceService.getSetTIByIdAndIdsCuon(searchTIList.getSetId(), searchTIList.getCuonIds()));
    }

    @PutMapping("/tu/{diemDoId}")
    public ResponseEntity<?> CreateTU(@CurrentUser CustomUserDetails userDetails, @RequestBody List<SetTUCreateDto> dto, @PathVariable String diemDoId) throws Exception {
        ResponseData res = new ResponseData();
        try {
            for (SetTUCreateDto setTU : dto) {
                deviceService.createOrUpdateTU(setTU, userDetails, diemDoId);
            }
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            res.setState(ResponseData.STATE.OK.toString());

        } catch (Exception ex) {
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
            res.setState(ResponseData.STATE.FAIL.toString());
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping("/tule/{diemDoId}")
    public ResponseEntity<?> CreateTUle(@CurrentUser CustomUserDetails userDetails, @RequestBody SetTUCreateDto dto, @PathVariable String diemDoId) throws Exception {
        return ResponseEntity.ok(deviceService.updateTU(dto, userDetails, diemDoId));
    }

    @PostMapping("/tile/{diemDoId}")
    public ResponseEntity<?> CreateTIle(@CurrentUser CustomUserDetails userDetails, @RequestBody SetTICreateDto dto, @PathVariable String diemDoId) throws Exception {
        return ResponseEntity.ok(deviceService.updateTI(dto, userDetails, diemDoId));

    }

    @PostMapping("/ti/{diemDoId}")
    public ResponseEntity<?> CreateTI(@CurrentUser CustomUserDetails userDetails, @RequestBody List<SetTICreateDto> dto, @PathVariable String diemDoId) throws Exception {
        ResponseData res = new ResponseData();
        try {
            for (SetTICreateDto setTI : dto) {
                deviceService.createOrUpdateTI(setTI, userDetails, diemDoId);
            }
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            res.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ex) {
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
            res.setState(ResponseData.STATE.FAIL.toString());
        }
        return ResponseEntity.ok(res);
    }

    @PutMapping("/congto-replace")
    public ResponseEntity<?> updateCongTo(@CurrentUser CustomUserDetails userDetails, @RequestBody CongToCrudDto dto) throws Exception {

        return ResponseEntity.ok(deviceService.updateCongTo(userDetails, dto));
    }

    @PutMapping("/tu-replace")
    public ResponseEntity<?> updateTU(@CurrentUser CustomUserDetails userDetails, @RequestBody SetTUCreateDto dto) throws Exception {

        return ResponseEntity.ok(deviceTUService.updateSetTu(dto, userDetails));
    }

    @PutMapping("/ti-replace")
    public ResponseEntity<?> updateTI(@CurrentUser CustomUserDetails userDetails, @RequestBody SetTICreateDto dto) throws Exception {

        return ResponseEntity.ok(deviceTIService.updateSetTi(dto, userDetails));
    }

    @GetMapping("/tu-odd")
    public ResponseEntity<?> getTUOdd(SearchTUList dto) {
        return ResponseEntity.ok(deviceTUService.getTUOdd(dto));
    }

    @GetMapping("/ti-odd")
    public ResponseEntity<?> getTIOdd(SearchTIList dto) {
        return ResponseEntity.ok(deviceTIService.getTIOdd(dto));
    }

    @PutMapping("/stu-replace/{id}")
    public ResponseEntity<?> ReplaceTU(@PathVariable("id") String id, @CurrentUser CustomUserDetails userDetails, @RequestBody DeviceCrudDto dto) {
        dto.setUserMDFId(userDetails.getUserid());
        return ResponseEntity.ok(deviceService.updateDevice(id, dto, A_ASSET.CategoryId.TU.toString()));
    }

    @GetMapping("/validateSerialNum/{serialNum}/{congToId}")
    public ResponseEntity<?> validateSerialNum(@PathVariable("serialNum") String serialNum, @PathVariable("congToId") String congToId) {
        return ResponseEntity.ok(deviceService.validateCongToSerialNum(serialNum, congToId));
    }

    @GetMapping("/tu-device")
    public ResponseEntity<?> getListTUChild(SearchTUList searchDto) {
        return ResponseEntity.ok(deviceService.getListTUChild(searchDto));
    }

    @GetMapping("/ti-device")
    public ResponseEntity<?> getListTIChild(SearchTIList searchDto) {
        return ResponseEntity.ok(deviceService.getListTIChild(searchDto));
    }

    @GetMapping("/cuon/{id}")
    public ResponseEntity<?> getCuonById(@PathVariable("id") String id) {
        return ResponseEntity.ok(deviceService.getCuonById(id));
    }

    @DeleteMapping("/record-temp/{transId}")
    public ResponseEntity<?> deleteRecordTemp(@PathVariable("transId") BigInteger transId) {
        return ResponseEntity.ok(deviceService.deleteRecordTemp(transId));
    }

    @PutMapping("/diem-do/{id}")
    public ResponseEntity<?> updateStatusDiemDo(@PathVariable String id, @RequestBody String status) {
        return ResponseEntity.ok(deviceService.updateStatusDiemDo(id, status));
    }

    @PostMapping("/checkDevice")
    public ResponseEntity<?> checkDevice(@RequestBody List<CheckDeviceDto> lstDevice) {
        return ResponseEntity.ok(deviceService.checkDevice(lstDevice));
    }

    @GetMapping("/all-set-tu")
    public ResponseEntity<?> getAllSetTU(SearchTUList dto) {
        return ResponseEntity.ok(deviceService.getAllSetTU(dto));
    }

    @GetMapping("/all-set-ti")
    public ResponseEntity<?> getAllSetTI(SearchTIList dto) {
        return ResponseEntity.ok(deviceService.getAllSetTI(dto));
    }

    @DeleteMapping("delete-cuon")
    public ResponseEntity<?> deleteCuonByIds(String idsCuon) {
        return ResponseEntity.ok(deviceService.deleteCuonByIds(idsCuon));
    }
    //lay cac danh sach thiet bi thuoc diem do
    @GetMapping("/asset-detail/{idDiemDo}")
    public ResponseEntity<?> getAssetDetail(@PathVariable String idDiemDo) {
        return ResponseEntity.ok(deviceService.getAssetDetail(idDiemDo));
    }
}
