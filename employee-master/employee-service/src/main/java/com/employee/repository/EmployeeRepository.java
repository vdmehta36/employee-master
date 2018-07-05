package com.employee.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.entity.EmployeeEntity;

/**
 * Spring Data Repository class for Employee. It is used for dao operations.
 *
 */
@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

	/**
	 * @param employeeNumber: Employee Number
	 * @return EmployeeEntity object
	 */
	@Query("select employeeEntity from EmployeeEntity employeeEntity where employeeEntity.employeeNumber = :employeeNumber")
	EmployeeEntity findEmployeeByEmpNumber(@Param("employeeNumber") String employeeNumber);

}
