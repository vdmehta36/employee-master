/**
 * 
 */
package com.employee.common.service;

import java.util.List;

import com.employee.common.dto.ICommonDTO;

import javassist.NotFoundException;

/**
 * Common Service interface containing basic read adn create operations.
 */
public interface ICommonService <T extends ICommonDTO>{

	/**
	 * @param dto : Generic Data Transfer Object
	 * @return T : T 
	 * @throws Exception : Generic exception.
	 */
	T create(T dto) throws Exception;	
	
	/**
	 * @return {@link ICommonService} : list of T
	 * @throws NotFoundException : Exception thrown if no such element found.
	 */
	List<T> readAll() throws NotFoundException;
}