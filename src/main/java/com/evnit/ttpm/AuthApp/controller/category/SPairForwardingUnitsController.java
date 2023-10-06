package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.SearchUnit;
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
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.PairForwardingUnitsCreateDto;
import com.evnit.ttpm.AuthApp.service.category.SPairForwardingUnitsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/category/pairforwarding")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)


public class SPairForwardingUnitsController {
	private final SPairForwardingUnitsService service;
    public SPairForwardingUnitsController(SPairForwardingUnitsService service) {
        this.service = service;
    }
    
    @ApiOperation(value = "Hàm lấy danh sách cặp đơn vị giao nhận", response = ResponseDataReport.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping()
   /* public ResponseEntity<?> getList(){
        try {
            return ResponseEntity.ok(service.getAll());
        }catch (Exception ex){
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }*/
    public ResponseEntity<?> getList(SearchUnit filter){
        try {
            return ResponseEntity.ok(service.getList(filter));
        }catch (Exception ex){
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }
    
    @ApiOperation(value = "Hàm thêm mới cặp đơn vị giao nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping
    public ResponseEntity<?> create(@CurrentUser CustomUserDetails user, @RequestBody PairForwardingUnitsCreateDto crudDto){
    	crudDto.setUserId(user.getUserid());
        ResponseData a = service.create(crudDto);
           return ResponseEntity.ok(a);
    }
    
    @ApiOperation(value = "Hàm cập nhật cặp đơn vị giao nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id,@CurrentUser CustomUserDetails user, @RequestBody PairForwardingUnitsCreateDto crudDto){
        crudDto.setUserId(user.getUserid());
        ResponseData a = service.update(id , crudDto);
           return ResponseEntity.ok(a);
    }
    
    @ApiOperation(value = "Hàm xóa cặp đơn vị giao nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
    		try {
                ResponseData a = service.delete(id);
                ResponseData dataRes = new ResponseData();
                dataRes.setData(id);
                dataRes.setState( ResponseData.STATE.OK.toString());
                dataRes.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                return ResponseEntity.ok(dataRes);
            }
            catch (Exception ex){
                return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
            }

    }
}
