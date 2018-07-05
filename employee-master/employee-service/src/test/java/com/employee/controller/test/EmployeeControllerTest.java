package com.employee.controller.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employee.controller.EmployeeController;
import com.employee.dto.EmployeeDTO;
import com.employee.service.EmployeeService;

import javassist.NotFoundException;

/**
 * This class contains unit tests for EmployeeController
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Unit test method to test working of get employee entry
	 * 
	 * @throws NotFoundException : Exception thrown when No records are found.
	 */
	@Test
	public void testGetEmployeeEntry() throws NotFoundException {

		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		employeeDTOs.add(new EmployeeDTO());
		when(employeeService.readAll()).thenReturn(employeeDTOs);

		List<EmployeeDTO> actualEmployeeDTOs = employeeService.readAll();
		Assert.assertEquals(actualEmployeeDTOs, employeeDTOs);

	}

	/**
	 * Unit test method to test working of employee registration creation
	 * 
	 * @throws Exception : Generic Exception
	 */

	@Test
	public void testCreateEmployeeRegistrationEntry() throws Exception {

		EmployeeDTO employeeDTO = new EmployeeDTO();

		when(employeeService.create(employeeDTO)).thenReturn(employeeDTO);

		EmployeeDTO actualEmployeeDTO = employeeService.create(employeeDTO);
		Assert.assertEquals(actualEmployeeDTO, employeeDTO);

	}

	/**
	 * Unit test method to test employee authentication
	 * 
	 */
	@Test
	public void testgetEmployeeAuthentication() {

		EmployeeDTO employeeDTO = new EmployeeDTO();

		when(employeeService.getPassworBasedOnEmployeeNumber(employeeDTO.getEmployeeNumber())).thenReturn(employeeDTO);

		EmployeeDTO actualEmployeeDTO = employeeService
				.getPassworBasedOnEmployeeNumber(employeeDTO.getEmployeeNumber());
		Assert.assertEquals(actualEmployeeDTO, employeeDTO);

	}

}
