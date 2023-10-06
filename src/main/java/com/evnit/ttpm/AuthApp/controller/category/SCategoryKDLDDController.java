package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.category.KDLDD.SearchKDLDDList;
import com.evnit.ttpm.AuthApp.model.category.WarningDevice.SearchWarningDeviceList;
import com.evnit.ttpm.AuthApp.service.category.SCategoryKDLDDService;
import com.evnit.ttpm.AuthApp.service.category.SCategoryPlanService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category/kdldd")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategoryKDLDDController {
    private final SCategoryKDLDDService service;

    public SCategoryKDLDDController(SCategoryKDLDDService service) {
        this.service = service;
    }

    @ApiOperation(value = "Hàm lấy danh sách trạm biến áp, ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping()



    public ResponseEntity<?> getList(SearchKDLDDList congToList) {
        return ResponseEntity.ok(service.getAll(congToList));
    }


}
