package com.employee.service.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.employee.dto.EmployeeDTO;
import com.employee.entity.EmployeeEntity;
import com.employee.mapper.EmployeeMapper;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

import javassist.NotFoundException;

/**
 * This class contains unit tests for EmployeeController
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeService employeeService;

	@Mock
	EmployeeRepository employeeRepository;

	@Mock
	EmployeeMapper employeeMapper;

	/**
	 * Unit test method to test working of get employee entry
	 * 
	 * @throws NoSuchFieldException : NoSuchFieldException
	 * @throws SecurityException : Security Exception
	 * @throws Exception : Generic Exception
	 */
	@Test
	public void testGetEmployeeEntry() throws NoSuchFieldException, SecurityException, Exception {

		List<EmployeeEntity> employeeEntities = new ArrayList<>();
		employeeEntities.add(new EmployeeEntity());

		when(employeeRepository.findAll()).thenReturn(employeeEntities);

		List<EmployeeDTO> employeeDTOs = new ArrayList<>();

		when(employeeMapper.convertToDTOList(employeeEntities)).thenReturn(employeeDTOs);
		setFinalStatic(EmployeeMapper.class.getField("INSTANCE"), employeeMapper);

		List<EmployeeDTO> actualEmployeeDTOs = employeeService.readAll();
		Assert.assertEquals(actualEmployeeDTOs, employeeDTOs);

	}

	/**
	 * Unit test method to test employee authentication
	 * 
	 * @throws NoSuchFieldException : NoSuchFieldException
	 * @throws SecurityException : SecurityException
	 * @throws Exception : Generic Exception
	 */
	@Test
	public void testGetPassworBasedOnEmployeeNumber() throws NoSuchFieldException, SecurityException, Exception {

		String employeeNumber = "A0001";

		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployeeNumber(employeeNumber);

		EmployeeEntity employeeEntity = new EmployeeEntity();

		when(employeeRepository.findEmployeeByEmpNumber(employeeNumber)).thenReturn(employeeEntity);
		when(employeeMapper.convertToDTO(employeeEntity)).thenReturn(employeeDTO);

		setFinalStatic(EmployeeMapper.class.getField("INSTANCE"), employeeMapper);

		EmployeeDTO actualEmployeeDTO = employeeService.getPassworBasedOnEmployeeNumber(employeeNumber);
		Assert.assertEquals(actualEmployeeDTO, employeeDTO);

	}

	/**
	 * Unit test method to test working of employee registration creation
	 * 
	 * @throws NoSuchFieldException :NoSuchFieldException
	 * @throws SecurityException :SecurityException
	 * @throws Exception  : Generic Exception
	 */
	@Test
	public void testCreateEmployeeEntry() throws NoSuchFieldException, SecurityException, Exception {

		setFinalStatic(EmployeeMapper.class.getField("INSTANCE"), employeeMapper);
		EmployeeDTO employeeDTO = new EmployeeDTO();

		EmployeeEntity employeeEntity = mock(EmployeeEntity.class);

		when(employeeMapper.convertToEntity(employeeDTO)).thenReturn(employeeEntity);
		when(employeeRepository.saveAndFlush(employeeEntity)).thenReturn(employeeEntity);
		when(employeeMapper.convertToDTO(employeeEntity)).thenReturn(employeeDTO);

		EmployeeDTO actualEmployeeDTO = employeeService.create(employeeDTO);
		Assert.assertEquals(actualEmployeeDTO, employeeDTO);

	}

	/**
	 * @throws NotFoundException : Exception thrown when No records are found.
	 */
	@Test(expected = NotFoundException.class)
	public void testGetEmployeeEntryThrowsException() throws NotFoundException {
		List<EmployeeEntity> employeeEntities = new ArrayList<>();
		when(employeeRepository.findAll()).thenReturn(employeeEntities);
		employeeService.readAll();
		
	}
	
	/**
	 * @param field : field
	 * @param newValue : new Value
	 * @throws Exception: Generic Exception
	 */
	static void setFinalStatic(Field field, Object newValue) throws Exception {
		field.setAccessible(true);

		// remove final modifier from field
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

		field.set(null, newValue);
	}

}
