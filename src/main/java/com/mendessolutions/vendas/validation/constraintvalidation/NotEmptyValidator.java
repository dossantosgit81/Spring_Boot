package com.mendessolutions.vendas.validation.constraintvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mendessolutions.vendas.validation.NotEmptyList;

@SuppressWarnings("rawtypes")
public class NotEmptyValidator 
implements ConstraintValidator<NotEmptyList, List>{

	@Override
	public boolean isValid(List list, ConstraintValidatorContext context) {
		return list != null && !list.isEmpty();
	}
	


}
