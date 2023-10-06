package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.SearchDonViSH;
import com.evnit.ttpm.AuthApp.model.category.tbargl.SearchTBAGRL;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglCrudDto;
import com.evnit.ttpm.AuthApp.service.category.SCategoryTbaRglService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/category/tbaRgl")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategoryTbaRglController {
    private final SCategoryTbaRglService service;
    public SCategoryTbaRglController(SCategoryTbaRglService service) {
        this.service = service;
    }
    @ApiOperation(value = "Hàm lấy danh sách trạm biến áp, ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping()
    public ResponseEntity<?> getList(SearchTBAGRL filter){
        try {
            return ResponseEntity.ok(service.getListTBA(filter));
        }catch (Exception ex){
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm thêm mới trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping
    public ResponseEntity<?> createTbaRgl(@CurrentUser CustomUserDetails userDetails,@RequestBody TbaRglCrudDto crudDto){
        crudDto.setUserId(userDetails.getUserid());
           var a = service.create(crudDto);
           return ResponseEntity.ok(a);
    }

    @ApiOperation(value = "Hàm cập nhật trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTbaRgl(@CurrentUser CustomUserDetails userDetails, @PathVariable("id") String id, @RequestBody TbaRglCrudDto crudDto){
        crudDto.setUserId(userDetails.getUserid());
        var a = service.update(id , crudDto);
           return ResponseEntity.ok(a);
    }@ApiOperation(value = "Hàm cập nhật trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTbaRgl(@PathVariable("id")String id){
           var a = service.delete(id);
           return ResponseEntity.ok(a);
    }
}
