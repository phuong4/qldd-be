package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.PQOBJ.PQOBJCreateDto;
import com.evnit.ttpm.AuthApp.model.Paramater.ParamaterCreateDto;
import com.evnit.ttpm.AuthApp.service.category.SCategoryPQOBJService;
import com.evnit.ttpm.AuthApp.service.category.SCategoryParamaterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/pqobj")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategoryPQOBJController {

    private final SCategoryPQOBJService service;
    public SCategoryPQOBJController(SCategoryPQOBJService service) {
        this.service = service;
    }



    @ApiOperation(value = "Hàm thêm mới trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping
    public ResponseEntity<?> createParamater(@RequestBody PQOBJCreateDto createDto) {
        var a = service.create(createDto);
        return ResponseEntity.ok(a);
    }

    @ApiOperation(value = "Hàm cập nhật trạm biến áp ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PutMapping("/{id}")
    public ResponseEntity<?> updateParamater(@PathVariable("id") int id, @RequestBody PQOBJCreateDto crudDto) {
        var a = service.update(id, crudDto);
        return ResponseEntity.ok(a);
    }



}
