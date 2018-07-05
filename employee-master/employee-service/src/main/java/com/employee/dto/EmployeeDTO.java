package com.employee.dto;

import javax.validation.constraints.NotNull;

import com.employee.common.dto.ICommonDTO;
import com.employee.common.exception.message.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Employee DTO class. 
 * It is a POJO class with getter-setter methods.
 *  *  
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({ "id" })
public class EmployeeDTO implements ICommonDTO {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(message = "Employee Name cannot be empty")
	private String employeeName;

	@NotNull(message = "Emloyee Number cannot be empty")
	private String employeeNumber;

	@NotNull(message = "Password cannot be empty")
	private String password;

	@NotNull(message = "Designation cannot be empty")
	private String designation;

	@NotNull(message = "Service Line cannot be empty")
	private String serviceLine;

	@NotNull(message = "Role cannot be empty")
	private String role;

	private String errorMessage;

	private String status;

	/**
	 * @return status: status 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status : status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return errorMessage: getting errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage : setting errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return id: getting Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id : setting id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return employeeName : getting employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName : setting employeeName
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return employeeNumber: getting employeeNumber
	 */
	public String getEmployeeNumber() {
		return employeeNumber;
	}

	/**
	 * @param employeeNumber: setting employeeNumber
	 */
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	/**
	 * @return password: getting password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password: setting password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return designation: getting designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation: setting designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return serviceLine: getting serviceLine
	 */
	public String getServiceLine() {
		return serviceLine;
	}

	/**
	 * @param serviceLine: setting serviceLine
	 */
	public void setServiceLine(String serviceLine) {
		this.serviceLine = serviceLine;
	}

	/**
	 * @return role: getting role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role:setting role
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
