package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.Paramater.ParamaterCreateDto;
import com.evnit.ttpm.AuthApp.model.Paramater.SearchParamaterList;
import com.evnit.ttpm.AuthApp.service.category.SCategoryParamaterService;
import com.evnit.ttpm.AuthApp.service.category.SCategoryPlanService;
import com.evnit.ttpm.AuthApp.service.category.SCategoryQuanLyDDService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/paramater")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategoryParamaterController {

    private final SCategoryParamaterService service;
    public SCategoryParamaterController(SCategoryParamaterService service) {
        this.service = service;
    }



    @ApiOperation(value = "Hàm thêm mới trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping
    public ResponseEntity<?> createParamater(@RequestBody ParamaterCreateDto createDto) {
        var a = service.create(createDto);
        return ResponseEntity.ok(a);
    }

    @ApiOperation(value = "Hàm cập nhật trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateParamater(@PathVariable("id") int id, @RequestBody ParamaterCreateDto crudDto) {
        var a = service.update(id, crudDto);
        return ResponseEntity.ok(a);
    }



}
