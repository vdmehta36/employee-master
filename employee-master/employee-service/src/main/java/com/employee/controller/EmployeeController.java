package com.employee.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.common.exception.message.MessageType;
import com.employee.common.utils.Messages;
import com.employee.common.utils.UIConstants;
import com.employee.dto.EmployeeDTO;
import com.employee.service.EmployeeService;

import javassist.NotFoundException;

/**
 * This class contains REST end-points for below functionality: a) To create a
 * new employee entry in database. b) To authenticate the employee details while
 * signing-up. c) To return the list of all employee details
 */

@RestController

public class EmployeeController {


	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Messages messages;

	/**
	 * It return all the employee details present in database in JSON format
	 * @param request : HttpServletRequest.
	 * @return EmployeeEntries : List of employee entries
	 * 
	 * @throws NotFoundException : Thrown when when such record is found in database.
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(path = UIConstants.UI_INQUIRY_GET_EMPLOYEE_DETAILS, method = RequestMethod.GET, produces = {
			"application/json; charset=utf-8" })

	@ResponseBody
	public List<EmployeeDTO> getEmployeesEntry(HttpServletRequest request) throws NotFoundException {
		List<EmployeeDTO> employeeDTOs = null;
		employeeDTOs = employeeService.readAll();

		return employeeDTOs;
	}

	/**
	 * Does user authentication
	 * 
	 * @param employeeDetails : All the details of employee
	 *@param request : HttpServletRequest
	 *@param response : HttpServletResponse
	 * @return true or false based on user authentication
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(path = UIConstants.EMPLOYEE_AUTHENTICATION, method = RequestMethod.POST, consumes = {
			"application/json; charset=utf-8" })
	@ResponseBody

	public EmployeeDTO getEmployeeAuthentication(HttpServletRequest request, HttpServletResponse response,
			@RequestBody EmployeeDTO employeeDetails) {

		EmployeeDTO employeeAuthenicated = null;

		if (null != employeeDetails.getEmployeeNumber() && null != employeeDetails.getPassword()) {

			EmployeeDTO employee = employeeService.getPassworBasedOnEmployeeNumber(employeeDetails.getEmployeeNumber());

			if (bCryptPasswordEncoder.matches(employeeDetails.getPassword(), employee.getPassword())) {
				employeeAuthenicated = employee;
				employeeAuthenicated.setPassword(null);
				createSessionCookie(response);

			} else
				response.setStatus(HttpStatus.NOT_FOUND.value());
		}
		return employeeAuthenicated;
	}

	private HttpServletResponse createSessionCookie(HttpServletResponse response) {
		if (null != response) {
			Cookie sessionCookie = new Cookie("emp-portal", "loggedin");
			sessionCookie.setHttpOnly(false);
			sessionCookie.setMaxAge(4800);
			sessionCookie.setSecure(true);
			response.addCookie(sessionCookie);
		}
		return response;
	}

	/**
	 * This method saves employee details
	 * 
	 * @param employeeRegistrationEntry : Details of employee registration coming from front end.
	 * @param request : HttpServletRequest
	 *@param response : HttpServletResponse
	 * @return Employee details : All the employee details except the password.
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(path = UIConstants.SAVE_EMPLOYEE_REGISTRATION_ENTRY, method = RequestMethod.POST, consumes = {
			"application/json; charset=utf-8" })
	@ResponseBody
	public EmployeeDTO createEmployeeRegistrationEntry(HttpServletRequest request, HttpServletResponse response,
			@RequestBody EmployeeDTO employeeRegistrationEntry) {

		EmployeeDTO dto = new EmployeeDTO();

		employeeRegistrationEntry.setPassword(bCryptPasswordEncoder.encode(employeeRegistrationEntry.getPassword()));

		try {
			dto = employeeService.create(employeeRegistrationEntry);
			createSessionCookie(response);
			dto.setPassword(null);
			dto.setStatus(MessageType.SUCCESS.name());
		} catch (Exception e) {

			dto.setStatus(MessageType.ERROR.name());
			dto.setErrorMessage(messages.get("emp.already.exists"));
		}
		return dto;
	}

}
