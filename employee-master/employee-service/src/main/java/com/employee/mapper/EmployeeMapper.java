package com.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.employee.common.mapper.IBaseMapper;
import com.employee.dto.EmployeeDTO;
import com.employee.entity.EmployeeEntity;

/**
 * Mapper to convert EmployeeEntity to EmployeeDTO
 * 
 */
@Mapper
public abstract class EmployeeMapper implements IBaseMapper<EmployeeDTO, EmployeeEntity> {

	/**
	 * Instance of EmployeeMapper
	 * 
	 */
	public static final EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

}
