package com.evnit.ttpm.AuthApp.controller.report;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.request.report.ReportViewRequest;
import com.evnit.ttpm.AuthApp.service.report.ReportViewService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report/view")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class ReportViewController {

    @Autowired
    ReportViewService reportViewService;




    // Hàm view report
    @ApiOperation(value = "Hàm view report", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @PostMapping("/{rptID}")
    public ResponseEntity<?> getViewReport(@PathVariable String rptID, @RequestBody ReportViewRequest req,
                                           @CurrentUser CustomUserDetails currentUser) {
        ResponseDataReport result = reportViewService.getViewReport(rptID, req.getPramReport(), req.getOrgid(),
                currentUser.getUserid());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/file/{rptID}")
    public ResponseEntity<?> getViewReportFileExcel(@PathVariable String rptID, @RequestBody ReportViewRequest req,
                                                    @CurrentUser CustomUserDetails currentUser) {
        return ResponseEntity.ok(reportViewService.getViewReportFileExcel(rptID, req.getPramReport(), req.getOrgid(),
                currentUser.getUserid()));
    }

    // Hàm view report
    @ApiOperation(value = "Hàm lấy danh sách report", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("/group/{rptGroupID}")
    public ResponseEntity<?> getListReportByGroup(@PathVariable String rptGroupID) {
        ResponseData result = reportViewService.getListReportByGroup(rptGroupID);
        return ResponseEntity.ok(result);
    }

    // Hàm view report
    @ApiOperation(value = "Hàm lấy danh sách report system", response = ResponseData.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("/system")
    public ResponseEntity<?> getListReportSystem() {
        ResponseData result = reportViewService.getListReportSystem();
        return ResponseEntity.ok(result);
    }






    @ApiOperation(value = "Hàm lấy danh sách report", response = ResponseData.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping("/all/{rptGroupID}")
    public ResponseEntity<?> getListReportByUser(@PathVariable String rptGroupID,@CurrentUser CustomUserDetails user) {
        ResponseData result = reportViewService.getListReportByUser(rptGroupID,user.getUserid());
        return ResponseEntity.ok(result);
    }

}
