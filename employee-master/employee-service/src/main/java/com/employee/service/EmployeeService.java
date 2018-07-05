package com.employee.service;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.employee.common.mapper.IBaseMapper;
import com.employee.common.service.impl.CommonServiceImpl;
import com.employee.dto.EmployeeDTO;
import com.employee.entity.EmployeeEntity;
import com.employee.mapper.EmployeeMapper;
import com.employee.repository.EmployeeRepository;

/**
 * Service class
 *
 */
@Service
public class EmployeeService extends CommonServiceImpl<EmployeeDTO, EmployeeEntity> {
	/**
	 * Autowiring repository layer
	 */
	@Resource
	private EmployeeRepository employeeRepository;

	/**
	 * @see com.employee.common.service.impl.CommonServiceImpl#getJPARepository()
	 */
	@Override
	public JpaRepository<EmployeeEntity, Long> getJPARepository() {
		return employeeRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.employee.common.service.impl.CommonServiceImpl#getMapper()
	 */
	/* (non-Javadoc)
	 * @see com.employee.common.service.impl.CommonServiceImpl#getMapper()
	 */
	@Override
	public IBaseMapper<EmployeeDTO, EmployeeEntity> getMapper() {
		return EmployeeMapper.INSTANCE;
	}

	
	/**Method to get Password Based on Employee Number
	 * @return encryptedPassword : Encrypted password 
	 *@param employeeNumber : Employee Number 
	 */
	
	public EmployeeDTO getPassworBasedOnEmployeeNumber(String employeeNumber) {

	
		return getMapper().convertToDTO(employeeRepository.findEmployeeByEmpNumber(employeeNumber));

	}

}
