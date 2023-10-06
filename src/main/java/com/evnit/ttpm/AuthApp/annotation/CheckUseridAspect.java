package com.evnit.ttpm.AuthApp.annotation;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.service.common.SCommService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

//Tham khảo sử dụng trong 2 class CheckUseridAuthorization, CheckUseridAuthorWrite
@Aspect
@Component
public class CheckUseridAspect {
	private final SCommService commService;
	
	@Autowired
	public CheckUseridAspect(SCommService commService) {
		this.commService = commService;
	}
	
	@Around("@annotation(authorization)")
	public Object checkFunctionRight(ProceedingJoinPoint pjp,
									 CheckUseridAuthorization authorization) throws Throwable {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUSer = (CustomUserDetails) auth.getPrincipal();

		String ids=authorization.functionid();
		//Kiểm tra quyền and
		String[] arr=ids.split("\\+");
		if (arr.length > 1) {
			for(int i=0;i<arr.length;i++)
				if (!commService.checkRightFunc(currentUSer.getUserid(), arr[i]))
					return new ResponseEntity<ResponseData>(new ResponseData(ResponseData.STATE.FAIL.toString(), "Access Denied"), HttpStatus.FORBIDDEN);
			return pjp.proceed();
		}

		//Kiểm tra quyền or
		arr=ids.split(",");
		if (arr.length > 0) {
			for(int i=0;i<arr.length;i++)
				if (commService.checkRightFunc(currentUSer.getUserid(), arr[i]))
					return pjp.proceed();
			return new ResponseEntity<ResponseData>(new ResponseData(ResponseData.STATE.FAIL.toString(), "Access Denied"), HttpStatus.FORBIDDEN);
		}

		//Không thuộc các trường hợp trên
		return new ResponseEntity<ResponseData>(new ResponseData(ResponseData.STATE.FAIL.toString(), "Access Denied"), HttpStatus.FORBIDDEN);

//		return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(),
//												  "Bạn không có quyền truy cập", null));
	}

	@Around("@annotation(authorization)")
	public Object checkFunctionRightWrite(ProceedingJoinPoint pjp,
										  CheckUseridAuthorWrite authorization) throws Throwable {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails currentUSer = (CustomUserDetails) auth.getPrincipal();
		String ids=authorization.functionid();
		//Kiểm tra quyền and
		String[] arr=ids.split("\\+");
		if (arr.length > 1) {
			for(int i=0;i<arr.length;i++)
				if (!commService.checkRightFuncWithWrite(currentUSer.getUserid(), arr[i]))
					return new ResponseEntity<ResponseData>(new ResponseData(ResponseData.STATE.FAIL.toString(), "Access Denied"), HttpStatus.FORBIDDEN);
			return pjp.proceed();
		}

		//Kiểm tra quyền or
		arr=ids.split(",");
		if (arr.length > 0) {
			for(int i=0;i<arr.length;i++)
				if (commService.checkRightFuncWithWrite(currentUSer.getUserid(), arr[i]))
					return pjp.proceed();
			return new ResponseEntity<ResponseData>(new ResponseData(ResponseData.STATE.FAIL.toString(), "Access Denied"), HttpStatus.FORBIDDEN);
		}

		//Không thuộc các trường hợp trên
		return new ResponseEntity<ResponseData>(new ResponseData(ResponseData.STATE.FAIL.toString(), "Access Denied"), HttpStatus.FORBIDDEN);

	}

}
