package com.evnit.ttpm.AuthApp.controller.samples;

import com.evnit.ttpm.AuthApp.annotation.CheckUseridAuthorWrite;
import com.evnit.ttpm.AuthApp.annotation.CheckUseridAuthorization;
import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.common.Params;
import com.evnit.ttpm.AuthApp.entity.samples.Customer;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.service.common.SCommService;
import com.evnit.ttpm.AuthApp.service.common.SRightIds;
import com.evnit.ttpm.AuthApp.service.samples.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//Tham khảo truyền nhiều tham số: @GetMapping("/api/employees/{id}/{name}")
//https://shareprogramming.net/huong-dan-su-dung-pathvariable-trong-spring-boot/

@RestController
@RequestMapping("/api/custormer")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class CustomerController {
    private final SCommService cSvc;
    private final CustomerService cusSv;

    public CustomerController(CustomerService cs, SCommService cSvc) {
        this.cusSv=cs;
        this.cSvc=cSvc;
    }

    // Hàm get theo ID
    @PreAuthorize("hasRole('ROLE_CUSTOMERFULL_NAME')")//test quyền: chú ý khi thêm role thì phải thêm vào cả com.evnit.ttpm.AuthApp.model.admin.RoleName
    @ApiOperation(value = "Lấy theo id", response = ResponseData.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping("/getById/{cusid}")
    public ResponseEntity<?> getById(@PathVariable String cusid) {
        try {
            ResponseData result = cusSv.getCustomerById(cusid);
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    //Cú pháp check quyền chức năng với các loại khác nhau:
    //@CheckUseridAuthorization(functionid = SRightIds.customer) //Một chức năng
    //@CheckUseridAuthorization(functionid = SRightIds.customer + "," + SRightIds.danh_muc_chung) //Nhiều chức năng or với nhau
    //@CheckUseridAuthorization(functionid = SRightIds.customer + "+" + SRightIds.danh_muc_chung) //Nhiều chức năng and với nhau


    // Hàm get all: có lồng kiểm tra quyền
    //Cú pháp Test quyền and các chức năng, nếu một chức năng thì chỉ để functionid = SRightIds.customer
    @CheckUseridAuthorization(functionid = SRightIds.customer)
    @ApiOperation(value = "Lấy tất cả: repository", response = ResponseData.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping("/getLstAll")
    public ResponseEntity<?> getLstAll() { //@CurrentUser CustomUserDetails user
        try {
            //Kiểm tra quyền
//            if (!cSvc.checkRightFunc(null,"01")) //user.getUserid()
//                return new ResponseEntity<ResponseData>(new ResponseData(ResponseData.STATE.FAIL.toString(), "Access Denied"), HttpStatus.FORBIDDEN);
            //Pass, xử lý
            ResponseData result = cusSv.getLstAllCustomer();
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    // Hàm getLst2Customer: thử nghiệm jdbcTemplate
    @ApiOperation(value = "Lấy danh sách theo tên khách hàng: jdbcTemplate", response = ResponseData.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping("/getLst2Customer/{name}")
    public ResponseEntity<?> getLst2Customer(@PathVariable String name) {
        try {
            ResponseData result = cusSv.getLst2Customer(name);
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    // Hàm getLst3Customer: thử nghiệm entityManager
    @ApiOperation(value = "Lấy danh sách theo tên khách hàng: entityManager", response = ResponseData.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping("/getLst3Customer/{name}")
    public ResponseEntity<?> getLst3Customer(@PathVariable String name) {
        try {
            ResponseData result = cusSv.getLst3Customer(name);
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    // Hàm getLst3Customer: thử nghiệm truy vấn tham số đặt tên
    @ApiOperation(value = "Lấy danh sách theo tên khách hàng: namedJdbcTemplate", response = ResponseData.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @GetMapping("/getLst4Customer/{name}")
    public ResponseEntity<?> getLst4Customer(@PathVariable String name) {
        try {
            ResponseData result = cusSv.getLst4Customer(name);
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    //Cú pháp check quyền chức năng thêm, sửa, xóa với các loại khác nhau:
    //@CheckUseridAuthorWrite(functionid = SRightIds.customer) //Một chức năng
    //@CheckUseridAuthorWrite(functionid = SRightIds.customer + "," + SRightIds.danh_muc_chung) //Nhiều chức năng or với nhau
    //@CheckUseridAuthorWrite(functionid = SRightIds.customer + "+" + SRightIds.danh_muc_chung) //Nhiều chức năng and với nhau

    //Hàm tạo khách hàng: lồng kiểm tra quyền
    @CheckUseridAuthorWrite(functionid = SRightIds.customer) //Kiểm tra quyền
    @ApiOperation(value = "Tạo mới khách hàng", response = ResponseData.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @PostMapping("/createCustomer")
    public ResponseEntity<?> createCustomer(@RequestBody @Valid Customer req,
                                            @CurrentUser CustomUserDetails currentUser) {
        try {
            //Kiểm tra quyền
//            if (!cSvc.checkRightFuncWithWrite(null,"01") && !cSvc.checkRightFuncWithWrite(null,"02") ) //user.getUserid()
//                return new ResponseEntity<ResponseData>(new ResponseData(ResponseData.STATE.FAIL.toString(), "Access Denied"), HttpStatus.FORBIDDEN);
            ResponseData result = cusSv.create(req, currentUser.getUserid());
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Cập nhật khách hàng", response = ResponseData.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @PutMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid Customer req,
                                            @CurrentUser CustomUserDetails currentUser) {
        try {
            ResponseData result = cusSv.update(req, currentUser.getUserid());
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "Xóa khách hàng", response = ResponseData.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @DeleteMapping("/delCustomer/{id}")
    public ResponseEntity<?> delCustomer(@PathVariable String id) {
        try {
            ResponseData result = cusSv.delete(id);
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }

    @ApiOperation(value = "callSP", response = ResponseData.class)
    @ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
    @PostMapping("/callSP")
    public ResponseEntity<?> callSP(@RequestBody @Valid Params req) {
        try {
            ResponseData result = cusSv.callSP(req.getP1());
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
        }
    }
}
