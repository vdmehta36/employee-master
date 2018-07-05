package com.employee.common.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;

import com.employee.common.dto.ICommonDTO;

/**
 *
 */
public class Validator{
	private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();	
	javax.validation.Validator validatorVariable = factory.getValidator();
	
	public void validate(List<? extends ICommonDTO> employeeEntry) throws ValidationException{
		List<String> errorMessages = new ArrayList<>();
		for(ICommonDTO element : employeeEntry) {
			Set<ConstraintViolation<ICommonDTO>> violations = validatorVariable.validate(element);
			for (ConstraintViolation<ICommonDTO> violation : violations) {
				errorMessages.add(violation.getMessage());
			}	
		}
		if(errorMessages.isEmpty())
			throw new ValidationException(errorMessages.toString());
	}

	public <T extends ICommonDTO> void validate(T dto) {
		List<String> errorMessages = new ArrayList<>();
		Set<ConstraintViolation<ICommonDTO>> violations = validatorVariable.validate(dto);
		for (ConstraintViolation<ICommonDTO> violation : violations) {
			errorMessages.add(violation.getMessage());
		}
		if(errorMessages.isEmpty())
			throw new ValidationException(errorMessages.toString());
	}
	
}
