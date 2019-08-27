package com.equipment.manager.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Target({
	ElementType.PARAMETER,
	ElementType.TYPE,
	ElementType.METHOD,
	ElementType.FIELD,
	ElementType.LOCAL_VARIABLE,
	ElementType.TYPE_PARAMETER,
	ElementType.ANNOTATION_TYPE,
	ElementType.CONSTRUCTOR,
	ElementType.MODULE,
	ElementType.PACKAGE,
	ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}
