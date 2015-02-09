package com.yotelopaso.persistence.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyLettersValidator implements ConstraintValidator<OnlyLetters, String> {

	@Override
	public void initialize(OnlyLetters constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		char[] chars = value.toCharArray();
		for (char c : chars) {
			if(!Character.isLetter(c) && !Character.isWhitespace(c)) {
				return false;
			}
		}
		return true;
	}

}
