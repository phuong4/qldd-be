package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.category.Plan.SearchPlanList;
import com.evnit.ttpm.AuthApp.model.category.SuCo.SearchSuCoList;
import com.evnit.ttpm.AuthApp.model.category.SuCo.SuCoCreateDto;
import com.evnit.ttpm.AuthApp.service.category.SCategoryPlanService;
import com.evnit.ttpm.AuthApp.service.category.SCategorySuCoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/plan")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategoryPlanController {
    private final SCategoryPlanService service;

    public SCategoryPlanController(SCategoryPlanService service) {
        this.service = service;
    }

    @ApiOperation(value = "Hàm lấy danh sách trạm biến áp, ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping()

    public ResponseEntity<?> getList(SearchPlanList congToList) {
        try{
            return ResponseEntity.ok(service.getAll(congToList));
        }
        catch (Exception ex){
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }


    }


}
