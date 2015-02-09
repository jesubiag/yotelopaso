package com.yotelopaso.persistence.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyLettersOrDashValidator implements ConstraintValidator<OnlyLettersOrDash, String> {

	@Override
	public void initialize(OnlyLettersOrDash constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) return true;
		char[] chars = value.toCharArray();
		int length = chars.length;
		for (int i=0; i < length; i++) {
			char c = chars[i];
			if(!Character.isLetter(c) && c != '-' && !Character.isWhitespace(c)) {
				return false;
			}
			if (c == '-') {
				if (i != 0 && i != (length-1)) {
					if (chars[i-1] == '-' || chars[i+1] == '-') {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		return true;
	}

}
