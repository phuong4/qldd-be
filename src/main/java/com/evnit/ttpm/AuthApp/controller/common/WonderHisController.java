package com.evnit.ttpm.AuthApp.controller.common;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.accreditation.*;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.common.SearchHisMountUnDto;
import com.evnit.ttpm.AuthApp.service.accreditation.AccreditationService;
import com.evnit.ttpm.AuthApp.service.system.WonderHisService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/worderhis/")
public class WonderHisController {
    @Autowired
    WonderHisService _wonderHisService;

    //Lấy danh sách lịch sử thao tác
    @ApiOperation(value = "Hàm lấy danh sách lịch sử thao tác", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("getListWorder/{worderId}/{type}")
    public ResponseEntity<?> getList(@PathVariable("worderId") String worderId, @PathVariable("type") String type) {
        try {
            return ResponseEntity.ok(_wonderHisService.GetList(worderId, type));
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    //Lấy danh sách lịch sử sự kiện
    @ApiOperation(value = "Hàm lấy danh sách lịch sử sự kiện", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("getListEvent/{Id}/{eventType}")
    public ResponseEntity<?> getListEvent(@PathVariable("Id") String Id, @PathVariable Integer eventType) {
        try {
            return ResponseEntity.ok(_wonderHisService.GetListEvent(Id, eventType));
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    //Lấy danh sách lịch sử gán gỡ
    @ApiOperation(value = "Hàm lấy danh sách lịch sử gán gỡ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("list-mount-un")
    public ResponseEntity<?> getListMountUnMout(SearchHisMountUnDto searchDto) {
        try {
            return ResponseEntity.ok(_wonderHisService.GetListMountUn(searchDto));
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }
}
