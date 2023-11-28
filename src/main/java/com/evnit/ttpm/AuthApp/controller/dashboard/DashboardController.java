package com.evnit.ttpm.AuthApp.controller.dashboard;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.evnit.ttpm.AuthApp.service.dashboard.DashboardService;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/api/dashboard/")
public class DashboardController {
    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @ApiOperation(value = "Hàm lấy tổng số lượng kiểm định, nghiệm thu, xử lý sự cố theo từng tháng của tất cả các nhà máy điện", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code=500, message = "")})
    @GetMapping("nmd")
    public ResponseEntity<?> getNmd() {
        try {
            return ResponseEntity.ok(dashboardService.getNmdDataToChart());
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm lấy tổng số lượng kiểm định, nghiệm thu, xử lý sự cố theo từng tháng của tất cả các trạm biến áp", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code=500, message = "")})
    @GetMapping("tba")
    public ResponseEntity<?> getTba() {
        try {
            return ResponseEntity.ok(dashboardService.getTbaDataToChart());
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm lấy tổng số lượng kiểm định, nghiệm thu, xử lý sự cố theo từng tháng của tất cả các ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code=500, message = "")})
    @GetMapping("rgl")
    public ResponseEntity<?> getRgl() {
        try {
            return ResponseEntity.ok(dashboardService.getRglDataToChart());
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm lấy tổng số lượng kiểm định theo từng tháng và từng loại nhà máy", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code=500, message = "")})
    @GetMapping("kd")
    public ResponseEntity<?> getKd() {
        try {
            return ResponseEntity.ok(dashboardService.getKdDataToChart());
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm lấy tổng số lượng nghiệm thu theo từng tháng và từng loại nhà máy", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code=500, message = "")})
    @GetMapping("nt")
    public ResponseEntity<?> getNt() {
        try {
            return ResponseEntity.ok(dashboardService.getNtDataToChart());
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Hàm lấy tổng số lượng xử lý sự cố theo từng tháng và từng loại nhà máy", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code=500, message = "")})
    @GetMapping("xlsc")
    public ResponseEntity<?> getXlsc() {
        try {
            return ResponseEntity.ok(dashboardService.getXlscDataToChart());
        } catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }
}