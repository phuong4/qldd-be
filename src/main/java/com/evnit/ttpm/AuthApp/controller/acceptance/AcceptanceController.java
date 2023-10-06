package com.evnit.ttpm.AuthApp.controller.acceptance;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.acceptance.AcceptanceChangeDto;
import com.evnit.ttpm.AuthApp.model.acceptance.AcceptanceDetailDto;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.acceptance.AcceptanceCrudDto;
import com.evnit.ttpm.AuthApp.model.acceptance.SearchAcceptanceList;
import com.evnit.ttpm.AuthApp.service.acceptance.AcceptanceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/acceptance")
public class AcceptanceController {
    private final AcceptanceService accecptanceService;

    @Autowired
    public AcceptanceController(AcceptanceService acceptanceService) {
        this.accecptanceService = acceptanceService;
    }

    @ApiOperation(value = "Hàm lấy danh thông tin nghiệm thu", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("")
    public ResponseEntity<?> getDanhSachThongTinKiemDinh(SearchAcceptanceList filter) {
        try {
            return ResponseEntity.ok(accecptanceService.getListAcceptanceToTable(filter));
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createAcceptance(@CurrentUser CustomUserDetails user, @RequestBody AcceptanceCrudDto dto) {
        dto.setUserCrId(user.getUserid());
        dto.setUserCrDTime(new Date());
        ResponseData response = new ResponseData();
        response.setState(ResponseData.STATE.OK.toString());
        response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        response.setData(null);
        var checkEX = accecptanceService.checkAcceptance(null, dto.getAssetId(), dto.getActionStartDate(), dto.getActionEndDate(), dto.getAcceptForm(), dto.getTypeAccept());
        if (checkEX) {
            try {
                response = accecptanceService.createAcceptance(dto);

                return ResponseEntity.ok(response);

            } catch (Exception e) {
                response.setState(ResponseData.STATE.FAIL.toString());
                response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                response.setData(null);
            }
        } else {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString() + "Đợt công tác bị trùng");
            response.setData(null);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateAcceptance(@PathVariable("id") String id, @CurrentUser CustomUserDetails user, @RequestBody AcceptanceCrudDto dto) throws Exception {
        dto.setUserCrId(user.getUserid());
        dto.setUserCrDTime(new Date());
        ResponseData response = new ResponseData();
        var checkEX = accecptanceService.checkAcceptance(id, dto.getAssetId(), dto.getActionStartDate(), dto.getActionEndDate(), dto.getAcceptForm(), dto.getTypeAccept());
        if (checkEX) {
            try {
                response = accecptanceService.updateAcceptance(id, dto);
            } catch (Exception ex) {
                response.setState(ResponseData.STATE.FAIL.toString());
                response.setMessage(ex.getMessage());
                response.setData(null);
            }
        } else {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString() + "Đợt công tác bị trùng");
            response.setData(null);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getAcceptDetails(@PathVariable String id) {
        return ResponseEntity.ok(accecptanceService.getAcceptDetail(id));
    }

    @GetMapping("change/{id}")
    public ResponseEntity<?> getAcceptChanges(@PathVariable String id) {
        return ResponseEntity.ok(accecptanceService.getAcceptChange(id));
    }

    // luu nghiem thu chi tiet
    @PostMapping("detail")
    public ResponseEntity<?> createAcceptanceDetail(@RequestBody AcceptanceDetailDto dto) {
        return ResponseEntity.ok(accecptanceService.createOrUpdateAcceptDetail(dto));
    }

    @PostMapping("change")
    public ResponseEntity<?> createAcceptanceChange(@RequestBody AcceptanceChangeDto dto) {
        return ResponseEntity.ok(accecptanceService.createOrUpdateAcceptChange(dto));
    }


    @ApiOperation(value = "Hàm cập nhật nql xác nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("updateNQL")
    public ResponseEntity<?> updateNQL(@CurrentUser CustomUserDetails user, String strACCEPTID, String ASSETID, Boolean stt) {
        var item = accecptanceService.updateNQL(user, strACCEPTID, ASSETID, stt);
        return ResponseEntity.ok(item);
    }

    @ApiOperation(value = "Hàm cập nhật nql xác nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("updateLDP")
    public ResponseEntity<?> updateLDP(@CurrentUser CustomUserDetails user, String strACCEPTID, String ASSETID, Boolean stt) {
        var item = accecptanceService.updateLDP(user, strACCEPTID, ASSETID, stt);
        return ResponseEntity.ok(item);
    }

    @ApiOperation(value = "Hàm danh sách file nghiệm thu", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("getFileNghiemThu")
    public ResponseEntity<?> getFileNghiemThu(String acceptance) {
        try {
            return ResponseEntity.ok(accecptanceService.getFileNghiemThu(acceptance));
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }

    }

    //Xóa thông tin kiểm định
    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteAcceptance(@PathVariable("id") String id) {
        var item = accecptanceService.deleteAcceptance(id);
        return ResponseEntity.ok(item);
    }
    //lay danh sach htdd nghiem thu
//    @GetMapping("")
//    public ResponseEntity<?> getAcceptanceDetails(SearchAcceptanceList searchListDto){
//        return ResponseEntity.ok(accecptanceService.getAcceptanceDetails(acceptId));
//    }
}
