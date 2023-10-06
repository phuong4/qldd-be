package com.evnit.ttpm.AuthApp.controller.admin;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.Function;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.S_Organization;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.service.admin.AdminService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class AdminController {
	@Autowired
	private AdminService adminService;

	@ApiOperation(value = "Hàm lấy tất cả các chức năng", response = Function.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })

	@GetMapping("/function/getall")
	public ResponseEntity<?> getAllQ_Function() {
		List<Function> result = adminService.getAllQ_Function();
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Hàm lấy tất cả các chức năng được phân quyền cho UserID", response = Function.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
	@GetMapping("/function/FindByUserId/{userid}")
	public ResponseEntity<?> getLstFuncByUserId(@PathVariable String userid) {
		List<Function> result = adminService.getLstFuncByUserId(userid, true, true);
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Hàm lấy tất cả danh sách đơn vị quản lý", response = S_Organization.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
	@GetMapping("/org/getall")
	public ResponseEntity<?> getAllOrg() {
		List<S_Organization> result = adminService.getALLOrg();
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Hàm lấy đơn vị quản lý theo user. Nếu trả về null thì mở ra thông báo là chưa được phân quyền. Direct về page logIn", response = S_Organization.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
	@GetMapping("/org/current/{userid}")
	public ResponseEntity<?> getOrgCurrent(@PathVariable String userid) {
		S_Organization result = adminService.getOrgCurrent(userid);
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Hàm lấy tất cả danh sách đơn vị quản lý theo mã cha", response = S_Organization.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })

	@GetMapping("/org/getallbyorgidparent/{orgid}")
	public ResponseEntity<?> getAllOrgByOrgIDParent(@PathVariable String orgid) {
		List<S_Organization> result = adminService.getALLOrgByParentID(orgid);
		return ResponseEntity.ok(result);
	}

	@ApiOperation(value = "Hàm lấy tất cả danh sách đơn vị quản lý theo Userid", response = S_Organization.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
	@GetMapping("/org/getallbyuserid")
	public ResponseEntity<?> getAllOrgByUserID(@CurrentUser CustomUserDetails currentUser) {
		ResponseData result = adminService.getALLOrgByUserID(currentUser.getUserid());

		return ResponseEntity.ok(result);
	}

}
