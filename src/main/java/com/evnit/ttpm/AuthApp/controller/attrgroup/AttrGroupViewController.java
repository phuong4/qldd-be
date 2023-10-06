package com.evnit.ttpm.AuthApp.controller.attrgroup;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.request.attrgroup.AttrGroupObjRequest;
import com.evnit.ttpm.AuthApp.service.attrgroup.AttrGroupViewService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attrgroup/view")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class AttrGroupViewController {

	private final AttrGroupViewService attrGroupViewService;

	public AttrGroupViewController(AttrGroupViewService attrGroupViewService) {
		this.attrGroupViewService = attrGroupViewService;
	}

	// Hàm view report
	@ApiOperation(value = "Hàm view report", response = ResponseDataReport.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
	@PostMapping("/getbyobj")
	public ResponseEntity<?> getAtrrGroupView(@RequestBody AttrGroupObjRequest req,
			@CurrentUser CustomUserDetails currentUser) {
		ResponseData result = attrGroupViewService.getAttGroupView(req.getObjID(), req.getObjTypeID(),
				currentUser.getUserid());
		return ResponseEntity.ok(result);
	}

}
