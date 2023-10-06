package com.evnit.ttpm.AuthApp.controller.common;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.entity.common.Params;
import com.evnit.ttpm.AuthApp.entity.common.SListAll;
import com.evnit.ttpm.AuthApp.entity.common.SListGroupAll;
import com.evnit.ttpm.AuthApp.service.common.SListAllService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/common/sListAll")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SListAllController {

    @Autowired
    SListAllService slstService;

    //p1: groupid_parent nếu có, root: thì để null cả input hoặc p1
    @ApiOperation(value = "Hàm lấy các nhóm danh mục cha", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping("/sListGroupGetLst")
    public ResponseEntity<?> sListGroupGetLst(@RequestBody(required = false) Params param) {
        try {
            ResponseData result;
            if (param==null)
                result = slstService.sListGroupGetLst(null);
            else
                result = slstService.sListGroupGetLst(param.getP1());
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    //p1: groupid
    @ApiOperation(value = "Hàm lấy chi tiết thông tin nhóm", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping("/sListGroupGet1")
    public ResponseEntity<?> sListGroupGet1(@RequestBody Params param) {
        try {
            ResponseData result;
            result = slstService.sListGroupGet1(param.getP1());
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm thêm mới nhóm", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping("/sListGroupCreate")
    public ResponseEntity<?> sListGroupCreate(@RequestBody SListGroupAll lg) {
        try {
            ResponseData result = slstService.sListGroupCreate(lg);
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm cập nhật nhóm", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping("/sListGroupUpdate")
    public ResponseEntity<?> sListGroupUpdate(@RequestBody SListGroupAll lg) {
        try {
            ResponseData result = slstService.sListGroupUpdate(lg);
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm xóa nhóm", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping("/sListGroupDel")
    public ResponseEntity<?> sListGroupDel(@RequestBody Params param) {
        try {
            ResponseData result = slstService.sListGroupDel(param.getP1());
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    //p1: groupid
    @ApiOperation(value = "Hàm lấy các danh mục theo nhóm danh mục", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping("/sListItemGetLst")
    public ResponseEntity<?> sListItemGetLst(@RequestBody Params param) {
        try {
            ResponseData result = slstService.sListItemGetLst(param.getP1());
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    //p1: listid
    @ApiOperation(value = "Hàm lấy chi tiết thông tin danh mục", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping("/sListItemGet1")
    public ResponseEntity<?> sListItemGet1(@RequestBody Params param) {
        try {
            ResponseData result;
            result = slstService.sListItemGet1(param.getP1());
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm thêm mới danh mục", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping("/sListItemCreate")
    public ResponseEntity<?> sListItemCreate(@RequestBody SListAll info) {
        try {
            ResponseData result = slstService.sListItemCreate(info);
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm cập nhật danh mục", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping("/sListItemUpdate")
    public ResponseEntity<?> sListItemUpdate(@RequestBody SListAll info) {
        try {
            ResponseData result = slstService.sListItemUpdate(info);
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    //p1: listid
    @ApiOperation(value = "Hàm xóa danh mục", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping("/sListItemDel")
    public ResponseEntity<?> sListItemDel(@RequestBody Params param) {
        try {
            ResponseData result = slstService.sListItemDel(param.getP1());
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }


}
