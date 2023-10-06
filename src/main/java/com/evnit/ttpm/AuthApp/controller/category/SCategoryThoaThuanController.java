package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.*;
import com.evnit.ttpm.AuthApp.service.category.SCategoryThoaThuanService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/thoathuan")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategoryThoaThuanController {
    private final SCategoryThoaThuanService service;

    public SCategoryThoaThuanController(SCategoryThoaThuanService service) {
        this.service = service;
    }

    @ApiOperation(value = "Hàm lấy danh sách trạm biến áp, ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping()



    public ResponseEntity<?> getList(SearchThoaThuanList congToList) {
        return ResponseEntity.ok(service.getAll(congToList));
    }

    @ApiOperation(value = "Hàm thêm mới trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping
    public ResponseEntity<?> createThoaThuan(@CurrentUser CustomUserDetails user,@RequestBody ThoaThuanCreateDto createDto) {
        createDto.setUSER_CR_ID(user.getUserid());
        createDto.setUserId(user.getUserid());
        var a = service.create(createDto);
        return ResponseEntity.ok(a);
    }

    @ApiOperation(value = "Hàm cập nhật trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateThoaThuan(@CurrentUser CustomUserDetails user, @PathVariable("id") String id, @RequestBody ThoaThuanCreateDto crudDto) {
        crudDto.setUserId(user.getUserid());
        var a = service.update(id, crudDto);
        return ResponseEntity.ok(a);
    }

    @ApiOperation(value = "Hàm cập nhật trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteThoaThuan(@PathVariable("id") String id) {
        var a = service.delete(id);
        return ResponseEntity.ok(a);
    }
    @ApiOperation(value = "Hàm danh sách file kiểm định", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("/{DEALID}")
    public ResponseEntity<?> getFileKiemDinh(@PathVariable("DEALID") String DEALID) {
        try {
            return ResponseEntity.ok(service.getFileKiemDinh(DEALID));
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }

    }
    @ApiOperation(value = "Hàm cập nhật nql xác nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/updateNQL")
    public ResponseEntity<?> updateNQL(@CurrentUser CustomUserDetails user,  String DEALID,String ASSETID,Boolean stt) {
        var item = service.updateNQL(user,DEALID,ASSETID, stt);
        return ResponseEntity.ok(item);
    }

    @ApiOperation(value = "Hàm cập nhật nql xác nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/updateLDP")
    public ResponseEntity<?> updateLDP( @CurrentUser CustomUserDetails user, String DEALID,String ASSETID,  Boolean stt) {
        var item = service.updateLDP(user,DEALID,ASSETID, stt);
        return ResponseEntity.ok(item);
    }
}
