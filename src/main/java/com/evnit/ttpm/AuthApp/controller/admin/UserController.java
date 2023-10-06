/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.controller.admin;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.admin.User;
import com.evnit.ttpm.AuthApp.model.payload.ApiResponse;
import com.evnit.ttpm.AuthApp.model.payload.LogOutRequest;
import com.evnit.ttpm.AuthApp.service.admin.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {

		this.userService = userService;

	}

	@GetMapping("/me")
	@PreAuthorize("hasRole('USER')")  //Check lại chỗ này phải comment mới được
	public ResponseEntity<?> getUserProfile(@CurrentUser CustomUserDetails currentUser) {
		return ResponseEntity.ok(currentUser);
	}

	@GetMapping("/admins")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllAdmins(@CurrentUser CustomUserDetails currentUser) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		System.out.println("@CurrentUser CustomUserDetails currentUser");
		System.out.println(currentUser);
		return ResponseEntity.ok("Hello. BBBBBBBBBBBBBBBBBBBBBBBBB ADMIN");
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser(@CurrentUser CustomUserDetails customUserDetails,
			@ApiParam(value = "Thông tin cung cấp logout API") @Valid @RequestBody LogOutRequest logOutRequest) {
		userService.logoutUser(customUserDetails, logOutRequest);
		return ResponseEntity.ok(new ApiResponse(true, "Đăng xuất thành công"));
	}

	// HaNV TEST
	@ApiOperation(value = "Get user info by User ID", response = User.class)
	@ApiResponses({ @io.swagger.annotations.ApiResponse(code = 404, message = "No user found"),
			@io.swagger.annotations.ApiResponse(code = 500, message = "") })
	@GetMapping("/userid")
	public ResponseEntity<?> getUserByUserId(@PathVariable("userid") String userid) {
		Optional<User> result = userService.findByUserId(userid);
		return ResponseEntity.ok(result);
	}

//	@ApiOperation(value = "Get user info by User Name", response = User.class)
//	@ApiResponses({
//			@io.swagger.annotations.ApiResponse(code=404,message = "No user found"),
//			@io.swagger.annotations.ApiResponse(code=500,message = "")
//	})
//	@GetMapping("/{username}")
//	public ResponseEntity<?> getUserById(@PathVariable String username) {
//		Optional<User> result = userService.findByUsername(username);
//		return ResponseEntity.ok(result);
//	}

	@ApiOperation(value = "Get user info by id", response = User.class)
	@ApiResponses({ @io.swagger.annotations.ApiResponse(code = 404, message = "No user found"),
			@io.swagger.annotations.ApiResponse(code = 500, message = "") })

	@GetMapping("/getlistall")
	public ResponseEntity<?> getListAllUser() {
		List<User> result = userService.findAll();
		return ResponseEntity.ok(result);
	}
}
