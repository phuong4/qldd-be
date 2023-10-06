package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.category.Plan.SearchPlanList;
import com.evnit.ttpm.AuthApp.model.category.WarningSystem.SearchWarningSystemList;
import com.evnit.ttpm.AuthApp.service.category.SCategoryPlanService;
import com.evnit.ttpm.AuthApp.service.category.SCategoryWarningSystemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/warningSystem")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategoryWarningSystemController {
    private final SCategoryWarningSystemService service;

    public SCategoryWarningSystemController(SCategoryWarningSystemService service) {
        this.service = service;
    }

    @ApiOperation(value = "Hàm lấy danh sách trạm biến áp, ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping()



    public ResponseEntity<?> getList(SearchWarningSystemList congToList) {
        return ResponseEntity.ok(service.getAll(congToList));
    }



}
