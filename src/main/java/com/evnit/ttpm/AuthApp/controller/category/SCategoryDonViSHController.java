package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.DonViSHCreateDto;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.SearchDonViSH;
import com.evnit.ttpm.AuthApp.service.category.SCategoryDonViSHService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/donvish")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategoryDonViSHController {
    private final SCategoryDonViSHService service;
    public SCategoryDonViSHController(SCategoryDonViSHService service) {
        this.service = service;
    }

    @ApiOperation(value = "Hàm lấy danh sách đơn vị sở hữu", response = ResponseDataReport.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping()
    public ResponseEntity<?> getList(SearchDonViSH filter){
        try {
            return ResponseEntity.ok(service.getListDonViSH(filter));
        }catch (Exception ex){
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm thêm mới đơn vị sở hữu", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping
    public ResponseEntity<?> createDonViSH(@CurrentUser CustomUserDetails userDetails, @RequestBody DonViSHCreateDto createDto){
        createDto.setUserId(userDetails.getUserid());
        var a = service.create(createDto);
           return ResponseEntity.ok(a);
    }

    @ApiOperation(value = "Hàm cập nhật đơn vị sở hữu", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDonViSH(@CurrentUser CustomUserDetails userDetails,@PathVariable("id")int id, @RequestBody DonViSHCreateDto crudDto){
        crudDto.setUserId(userDetails.getUserid());
           var a = service.update(id , crudDto);
           return ResponseEntity.ok(a);
    }

    @ApiOperation(value = "Hàm xóa đơn vị sở hữu", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    //@DeleteMapping("/{id}")
    
    @RequestMapping(value="{id}", method=RequestMethod.DELETE)

    public ResponseEntity<?> deleteDonViSH(@PathVariable("id")int id){
           var a = service.delete(id);
           return ResponseEntity.ok(a);
    }
}
