package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.SearchDeliveryUnit;
import com.evnit.ttpm.AuthApp.model.category.TinhTP.*;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglCrudDto;
import com.evnit.ttpm.AuthApp.service.category.SCategoryTinhTPService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableWebSecurity(debug = false)
@RequestMapping("/api/category/tinhthanhpho")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategoryTinhTPController {
    private final SCategoryTinhTPService service;
    public SCategoryTinhTPController(SCategoryTinhTPService service) {
        this.service = service;
    }

    @ApiOperation(value = "Hàm lấy danh sách trạm biến áp, ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping()
    public ResponseEntity<?> getList(SearchDeliveryUnit filter){
        try {
            return ResponseEntity.ok(service.getList(filter));
        }catch (Exception ex){
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm thêm mới trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping
    public ResponseEntity<?> createTinhTP(@CurrentUser CustomUserDetails userDetails,@RequestBody TinhTPCreateDto createDto){
        createDto.setUserId(userDetails.getUserid());
        var a = service.create(createDto);
           return ResponseEntity.ok(a);
    }

    @ApiOperation(value = "Hàm cập nhật trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("{id}")
    public ResponseEntity<?> updateTinhThanhPho(@CurrentUser CustomUserDetails userDetails,@PathVariable("id")int id, @RequestBody TinhTPCreateDto crudDto){
        crudDto.setUserId(userDetails.getUserid());
        var a = service.update(id , crudDto);
           return ResponseEntity.ok(a);
    }

    @ApiOperation(value = "Hàm cập nhật trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    //@DeleteMapping("id")
    @RequestMapping(value="{id}", method=RequestMethod.DELETE)

    public ResponseEntity<?> deleteTinhThanhPho(@PathVariable("id")int id){
           var a = service.delete(id);
           return ResponseEntity.ok(a);
    }

    @ApiOperation(value = "Hàm lấy danh sách trạm biến áp, ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping("/tinhTPPaging")
    public ResponseEntity<?> fetchTinhTPDataAsPageWithFiltering(@RequestParam(defaultValue = "") String nameFilter,
                                                                @RequestParam(defaultValue = "0") int domain,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "20") int size){
        return ResponseEntity.ok(service.fetchTinhTPDataAsPageWithFiltering(nameFilter, domain, page, size));
    }
}
