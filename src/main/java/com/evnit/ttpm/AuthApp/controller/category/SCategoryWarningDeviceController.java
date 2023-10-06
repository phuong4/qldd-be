package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.category.Plan.SearchPlanList;
import com.evnit.ttpm.AuthApp.model.category.WarningDevice.SearchWarningDeviceList;
import com.evnit.ttpm.AuthApp.service.category.SCategoryPlanService;
import com.evnit.ttpm.AuthApp.service.category.SCategoryWarningDeviceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/warningDevice")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategoryWarningDeviceController {
    private final SCategoryWarningDeviceService service;

    public SCategoryWarningDeviceController(SCategoryWarningDeviceService service) {
        this.service = service;
    }

    @ApiOperation(value = "Hàm lấy danh sách trạm biến áp, ranh giới lẻ", response = ResponseDataReport.class)
    @ApiResponses({@ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "")})
    @GetMapping()



    public ResponseEntity<?> getList(SearchWarningDeviceList congToList) {
        return ResponseEntity.ok(service.getAll(congToList));
    }
    //Lấy chi thông tin kiểm định TU, TI
    @GetMapping("/getThongTinKiemDinhTUTI/{accredId}/{strThietBi}")
    public ResponseEntity<?> GetThongTinKiemDinhTUTI(@PathVariable("accredId") String accredId, @PathVariable("strThietBi") String strThietBi) {
        return ResponseEntity.ok(service.GetThongTinKiemDinhTUTI(accredId, strThietBi));
    }
    //Lấy chi thông tin kiểm định TU, TI
    @GetMapping("/getThongTinKiemDinhTI/{accredId}/{strThietBi}")
    public ResponseEntity<?> GetThongTinKiemDinhTI(@PathVariable("accredId") String accredId, @PathVariable("strThietBi") String strThietBi) {
        return ResponseEntity.ok(service.GetThongTinKiemDinhTI(accredId, strThietBi));
    }


}
