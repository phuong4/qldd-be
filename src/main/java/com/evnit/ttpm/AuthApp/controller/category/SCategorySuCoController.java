package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.SuCo.SearchSuCoList;
import com.evnit.ttpm.AuthApp.model.category.SuCo.SuCoCreateDto;
import com.evnit.ttpm.AuthApp.service.category.SCategorySuCoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/suco")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategorySuCoController {
    private final SCategorySuCoService service;

    public SCategorySuCoController(SCategorySuCoService service) {
        this.service = service;
    }

    @ApiOperation(value = "Hàm lấy danh sách trạm biến áp, ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping()



    public ResponseEntity<?> getList(SearchSuCoList congToList) {
        return ResponseEntity.ok(service.getAll(congToList));
    }

    @ApiOperation(value = "Hàm thêm mới trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping
    public ResponseEntity<?> createSuCo(@CurrentUser CustomUserDetails user,@RequestBody SuCoCreateDto createDto) {
        createDto.setUSER_CR_ID(user.getUserid());
        var a = service.create(createDto);
        return ResponseEntity.ok(a);
    }

    @ApiOperation(value = "Hàm cập nhật trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSuCo(@CurrentUser CustomUserDetails user,@PathVariable("id") String id, @RequestBody SuCoCreateDto crudDto) {
        crudDto.setUSER_CR_ID(user.getUserid());
        var a = service.update(id, crudDto);
        return ResponseEntity.ok(a);
    }

    @ApiOperation(value = "Hàm cập nhật trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSuCo(@PathVariable("id") String id) {
        var a = service.delete(id);
        return ResponseEntity.ok(a);
    }
    @ApiOperation(value = "Hàm danh sách file kiểm định", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("/{KeyId}")
    public ResponseEntity<?> getFileKiemDinh(@PathVariable("KeyId") String KeyId) {
        try {
            return ResponseEntity.ok(service.getFileKiemDinh(KeyId));
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }

    }
    @ApiOperation(value = "Hàm cập nhật nql xác nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/updateNQL")
    public ResponseEntity<?> updateNQL(@CurrentUser CustomUserDetails user,  String PROBLEMID,String ASSETID,Boolean stt) {
        var item = service.updateNQL(user,PROBLEMID,ASSETID, stt);
        return ResponseEntity.ok(item);
    }

    @ApiOperation(value = "Hàm cập nhật nql xác nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/updateLDP")
    public ResponseEntity<?> updateLDP(@CurrentUser CustomUserDetails user,  String PROBLEMID,String ASSETID,  Boolean stt) {
        var item = service.updateLDP(user,PROBLEMID,ASSETID, stt);
        return ResponseEntity.ok(item);
    }
}
