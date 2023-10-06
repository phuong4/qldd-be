package com.evnit.ttpm.AuthApp.controller.report;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.service.report.ReportGroupViewService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportgroup/view")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class ReportGroupViewController {

    @Autowired
    ReportGroupViewService reportGroupViewService;


    @ApiOperation(value = "Hàm lấy danh sách report group by group parent", response = ResponseData.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("/byparent/{rptGroupID}")
    public ResponseEntity<?> getListReportGroupByParent(@PathVariable String rptGroupID) {
        ResponseData result = reportGroupViewService.getListReportGroupByParent(rptGroupID);
        return ResponseEntity.ok(result);
    }


    @ApiOperation(value = "Hàm lấy danh sách report group", response = ResponseData.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("")
    public ResponseEntity<?> getListReportGroupByUser(@CurrentUser CustomUserDetails user) {
        ResponseData result = reportGroupViewService.getListReportGroupByUser(user.getUserid());
        return ResponseEntity.ok(result);
    }

}
