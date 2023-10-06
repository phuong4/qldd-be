package com.evnit.ttpm.AuthApp.controller.device;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.accreditation.*;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.service.accreditation.AccreditationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/accreditation/")
public class AccreditationController {
    @Autowired
    AccreditationService accreditationService;

//    //thêm mới thiết bị kiểm định vào bảng tạm
//    @PostMapping("accreditationDetailTemp")
//    public ResponseEntity<?> CreateCongTo(@RequestBody List<AccreditationDetailDto> dto) {
//        ResponseData response = new ResponseData();
//
//        //lặp lưu từng phần tử vào bảng tạm
//        for (AccreditationDetailDto item : dto) {
//            response = accreditationService.createAccreditation(item);
//        }
//        return ResponseEntity.ok(response);
//    }

//    //thêm chi tiết kiểm định công tơ vào bảng tạm
//    @PostMapping("accreditationResultMeterTemp")
//    public ResponseEntity<?> CreateResultMeterTemp(@RequestBody AccreditationResultMeterDataDto dto, @CurrentUser CustomUserDetails userDetails) {
//        ResponseData response = new ResponseData();
//
////        dto.setNguoi_cnhat(userDetails.getUserid());
//
//        //lưu từng phần tử vào bảng tạm
//        response = accreditationService.createAccreditationResultMeter(dto);
//
//        return ResponseEntity.ok(response);
//    }

//    //thêm chi tiết kiểm định TU vào bảng tạm
//    @PostMapping("accreditationResultTUTemp")
//    public ResponseEntity<?> CreateResultTUTemp(@RequestBody AccreditationResultTUDataDto dto, @CurrentUser CustomUserDetails userDetails) {
//        ResponseData response = new ResponseData();
//
//        dto.setNguoi_cnhat(userDetails.getUserid());
//
//        //lưu từng phần tử vào bảng tạm
//        response = accreditationService.createAccreditationResultTU(dto);
//
//        return ResponseEntity.ok(response);
//    }

    //lấy danh sách thiết bị kiểm định đã add vào bảng tạm theo transId
    @ApiOperation(value = "Hàm lấy danh sách công tơ kiểm định đã add vào bảng tạm", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("getAccreditationDetails/{transId}/{type}/{accredId}")
    public ResponseEntity<?> getAccreditationDetails(@PathVariable("transId") String transId, @PathVariable("type") String type, @PathVariable("accredId") String accredId) {
        try {
            return ResponseEntity.ok(accreditationService.getAccreditationDetails(transId, type, accredId));
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    //Lấy chi tiết kiểm định công tơ
    @ApiOperation(value = "Hàm lấy danh sách chi tiết kiểm định công tơ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("getListResultMeterTemp/{accredDetailId}/{assetId}")
    public ResponseEntity<?> getListResultMeterTemp(@PathVariable("accredDetailId") String accId, @PathVariable("assetId") String assetId) {
        try {
            return ResponseEntity.ok(accreditationService.getResultMeter(accId, assetId));
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    //lấy danh sách TU để kiểm định
    @GetMapping("thiet-bi-kd/{transId}/{categoryId}")
    public ResponseEntity<?> GetThietBiKD(@PathVariable("transId") String transId, @PathVariable("categoryId") String categoryId, String strThietBi, String nhaMayDienId) {
        return ResponseEntity.ok(accreditationService.getThietBiKD(transId, categoryId, strThietBi, nhaMayDienId));
    }

    //Lấy chi thông tin kiểm định TU, TI
    @GetMapping("getThongTinKiemDinhTUTI/{accredId}/{strThietBi}")
    public ResponseEntity<?> GetThongTinKiemDinhTUTI(@PathVariable("accredId") String accredId, @PathVariable("strThietBi") String strThietBi) {
        return ResponseEntity.ok(accreditationService.GetThongTinKiemDinhTUTI(accredId, strThietBi));
    }

    //Lấy chi thông tin kiểm định TI
    @GetMapping("getThongTinKiemDinhTI/{accredId}/{strThietBi}")
    public ResponseEntity<?> GetThongTinKiemDinhTI(@PathVariable("accredId") String accredId, @PathVariable("strThietBi") String strThietBi) {
        return ResponseEntity.ok(accreditationService.GetThongTinKiemDinhTI(accredId, strThietBi));
    }

//    //thêm chi tiết kiểm định TI vào bảng tạm
//    @PostMapping("accreditationResultTITemp")
//    public ResponseEntity<?> CreateResultTITemp(@RequestBody AccreditationResultTIDataDto dto, @CurrentUser CustomUserDetails userDetails) {
//        ResponseData response = new ResponseData();
//
//        dto.setNguoi_cnhat(userDetails.getUserid());
//
//        //lưu từng phần tử vào bảng tạm
//        response = accreditationService.createAccreditationResultTI(dto);
//
//        return ResponseEntity.ok(response);
//    }

    //Lưu thông tin lần kiểm định
    @PostMapping("createAccreditation")
    public ResponseEntity<?> CreateAccreditation(@RequestBody AccreditationDto dto, @CurrentUser CustomUserDetails userDetails) {
        ResponseData response = new ResponseData();
        response.setState(ResponseData.STATE.OK.toString());
        response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        response.setData(null);

        dto.setUser_cr_id(userDetails.getUserid());
        var checkEX = accreditationService.checkAccreditation(null, dto.getAssetId(), dto.getAccredtationStartDate(), dto.getAccredtationEndDate(), dto.getAccredtationType());

        if (checkEX) {
            try {
                response = accreditationService.CreateAccreditation(dto);
                return ResponseEntity.ok(response);

            } catch (Exception e) {
                response.setState(ResponseData.STATE.FAIL.toString());
                response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                response.setData(null);
            }
        } else {
            response.setState(ResponseData.STATE.WARNING.toString());
            response.setMessage(ResponseData.MESSAGE.WARNING.toString());
            response.setData("Đợt công tác bị trùng");
        }

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Hàm lấy danh thông tin kiểm định", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("getDanhSachThongTinKiemDinh")
    public ResponseEntity<?> getDanhSachThongTinKiemDinh(SearchKiemDinh filter) {
        try {
            return ResponseEntity.ok(accreditationService.getDanhSachThongTinKiemDinh(filter));
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    //Xóa thiết bị kiểm định công tơ đã lưu vào bảng tạm
    @RequestMapping(value = "deleteAccreditationDetailMeterTemp/{accDetailId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccreditationDetailTemp(@PathVariable("accDetailId") String accDetailId) {
        //var item = accreditationService.deleteAccreditationDetailMeter(accDetailId);
        return ResponseEntity.ok(1);
    }

    //Xóa thiết bị kiểm định TU đã lưu vào bảng tạm
    @RequestMapping(value = "deleteAccreditationDetailTUTemp/{accDetailId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccreditationDetailTUTemp(@PathVariable("accDetailId") String accDetailId) {
        //var item = accreditationService.deleteAccreditationDetailTU(accDetailId);
        return ResponseEntity.ok(1);
    }

    //Xóa thiết bị kiểm định TI đã lưu vào bảng tạm
    @RequestMapping(value = "deleteAccreditationDetailTITemp/{accDetailId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccreditationDetailTITemp(@PathVariable("accDetailId") String accDetailId) {
        //var item = accreditationService.deleteAccreditationDetailTI(accDetailId);
        return ResponseEntity.ok(1);
    }

    //Xóa thông tin kiểm định
    @DeleteMapping(value = "deleteAccreditation/{id}")
    public ResponseEntity<?> deleteAccreditation(@PathVariable("id") String id) {
        var item = accreditationService.deleteAccreditation(id);
        return ResponseEntity.ok(item);
    }

    @ApiOperation(value = "Hàm cập nhật kiểm định", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("updateAccreditation/{id}")
    public ResponseEntity<?> updateAccreditation(@PathVariable("id") String id, @RequestBody AccreditationDto dto, @CurrentUser CustomUserDetails userDetails) {
        dto.setUser_mdf_id(userDetails.getUserid());

        ResponseData response = new ResponseData();
        var checkEX = accreditationService.checkAccreditation(id, dto.getAssetId(), dto.getAccredtationStartDate(), dto.getAccredtationEndDate(), dto.getAccredtationType());

        if (checkEX) {
            try {
                response = accreditationService.UpdateAccreditation(id, dto);
            } catch (Exception ex) {
                response.setState(ResponseData.STATE.FAIL.toString());
                response.setMessage(ex.getMessage());
                response.setData(null);
            }
        } else {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData("Đợt công tác bị trùng");
        }

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Hàm cập nhật nql xác nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("updateNQL")
    public ResponseEntity<?> updateNQL(@CurrentUser CustomUserDetails user, String strAccerdId, String ASSETID, Boolean stt) {
        var item = accreditationService.updateNQL(user, strAccerdId, ASSETID, stt);
        return ResponseEntity.ok(item);
    }

    @ApiOperation(value = "Hàm cập nhật ldp xác nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("updateLDP")
    public ResponseEntity<?> updateLDP(@CurrentUser CustomUserDetails user, String strAccerdId, String ASSETID, Boolean stt) {
        var item = accreditationService.updateLDP(user, strAccerdId, ASSETID, stt);
        return ResponseEntity.ok(item);
    }

    @ApiOperation(value = "Hàm danh sách file kiểm định", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("getFileKiemDinh")
    public ResponseEntity<?> getFileKiemDinh(String accredId) {
        try {
            return ResponseEntity.ok(accreditationService.getFileKiemDinh(accredId));
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }

    }
}
