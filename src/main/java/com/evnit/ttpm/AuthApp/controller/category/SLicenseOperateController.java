package com.evnit.ttpm.AuthApp.controller.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.category.LicenseOperate.LicenseOperateCreateDto;
import com.evnit.ttpm.AuthApp.service.category.SLicenseOperateServcie;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/category/license")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)

public class SLicenseOperateController {
	
	private final SLicenseOperateServcie service;
    public SLicenseOperateController(SLicenseOperateServcie service) {
        this.service = service;
    }
    
    @ApiOperation(value = "Hàm lấy danh sách giấy phép hoạt động", response = ResponseDataReport.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping()
    public ResponseEntity<?> getList(){
        try {
            return ResponseEntity.ok(service.getAll());
        }catch (Exception ex){
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }
    
    @ApiOperation(value = "Hàm thêm mới giấy phép hoạt động", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping
    public ResponseEntity<?> create(@RequestBody LicenseOperateCreateDto crudDto){
    		ResponseData a = service.create(crudDto);
           return ResponseEntity.ok(a);
    }
    
    @ApiOperation(value = "Hàm cập nhật giấy phép hoạt động", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody LicenseOperateCreateDto crudDto){
    		ResponseData a = service.update(id , crudDto);
           return ResponseEntity.ok(a);
    }
    
    @ApiOperation(value = "Hàm cập nhật xóa giấy phép hoạt động", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
    		ResponseData a = service.delete(id);
           return ResponseEntity.ok(a);
    }
}
