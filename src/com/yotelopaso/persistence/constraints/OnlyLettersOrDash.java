package com.yotelopaso.persistence.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy=OnlyLettersOrDashValidator.class)
@Documented
public @interface OnlyLettersOrDash {

	String message() default "El campo puede contener solamente letras o un guión entremedio";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
