package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.SearchDeliveryUnit;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.SearchUnit;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.SearchNMD;
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
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.DeliveryUnitCreateDto;
import com.evnit.ttpm.AuthApp.service.category.SDeliveryUnitService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/category/deliveryunit")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)

public class SDeliveryUnitController {


	private final SDeliveryUnitService service;
	
    public SDeliveryUnitController(SDeliveryUnitService service) {
        this.service = service;
    }
    
    @ApiOperation(value = "Hàm lấy danh đơn vị giao nhận", response = ResponseDataReport.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping()
    public ResponseEntity<?> getList(SearchDeliveryUnit filter){
        try {
            return ResponseEntity.ok(service.getList(filter));
        }catch (Exception ex){
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }
    
    @ApiOperation(value = "Hàm thêm mới đơn vị giao nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping
    public ResponseEntity<?> create(@RequestBody DeliveryUnitCreateDto crudDto,@CurrentUser CustomUserDetails user){
    	crudDto.setUserId(user.getUserid());
        ResponseData a = service.create(crudDto);
           return ResponseEntity.ok(a);
    }
    
    @ApiOperation(value = "Hàm cập nhật đơn vị giao nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody DeliveryUnitCreateDto crudDto,@CurrentUser CustomUserDetails user){
        crudDto.setUserId(user.getUserid());
        ResponseData a = service.update(id , crudDto);
           return ResponseEntity.ok(a);
    }
    
    @ApiOperation(value = "Hàm xóa đơn vị giao nhận", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        try{
            ResponseData a = service.delete(id);
            ResponseData dataRes = new ResponseData();
            dataRes.setData(id);
            dataRes.setState( a.getState());
            dataRes.setMessage(a.getMessage());
            return ResponseEntity.ok(dataRes);
        }
        catch (Exception ex){
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }

    }
}
