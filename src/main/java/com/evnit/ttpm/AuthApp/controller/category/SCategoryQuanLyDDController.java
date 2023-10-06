package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.DonViSHCreateDto;
import com.evnit.ttpm.AuthApp.model.category.QuanLyDD.*;
import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.SearchThoaThuanList;
import com.evnit.ttpm.AuthApp.service.category.SCategoryQuanLyDDService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/quanlydd")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategoryQuanLyDDController {
    private final SCategoryQuanLyDDService service;
    public SCategoryQuanLyDDController(SCategoryQuanLyDDService service) {
        this.service = service;
    }
    @ApiOperation(value = "Hàm lấy danh sách đo đếm", response = ResponseDataReport.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping()
    public ResponseEntity<?> getList(SearchQLDDList congToList) {
        try {
            return ResponseEntity.ok(service.getAll(congToList));
        }catch (Exception ex){
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }
    @ApiOperation(value = "Hàm thêm mới quản lý đo đếm", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping
    public ResponseEntity<?> createQuanLyDD(@CurrentUser CustomUserDetails user,@RequestBody QuanLyDDCreateDto createDto){
        createDto.setUserId(user.getUserid());
        var a = service.create(createDto);
           return ResponseEntity.ok(a);
    }
    @ApiOperation(value = "Hàm cập nhật quản lý đo đếm", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuanLyDD(@CurrentUser CustomUserDetails user,@PathVariable("id")String id, @RequestBody QuanLyDDCreateDto crudDto){
        crudDto.setUserId(user.getUserid());
        var a = service.update(id , crudDto);
           return ResponseEntity.ok(a);
    }@ApiOperation(value = "Hàm cập nhật quản lý đo đếm", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuanLyDD(@PathVariable("id")String id){
           var a = service.delete(id);
           return ResponseEntity.ok(a);
    }
}
