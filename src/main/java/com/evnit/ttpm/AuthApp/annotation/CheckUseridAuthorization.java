package com.evnit.ttpm.AuthApp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Cú pháp check quyền chức năng với các loại khác nhau:
//@CheckUseridAuthorization(functionid = SRightIds.customer) //Một chức năng
//@CheckUseridAuthorization(functionid = SRightIds.customer + "," + SRightIds.danh_muc_chung) //Nhiều chức năng or với nhau
//@CheckUseridAuthorization(functionid = SRightIds.customer + "+" + SRightIds.danh_muc_chung) //Nhiều chức năng and với nhau

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckUseridAuthorization {
	String functionid();
}
