/**
 * 
 */
package com.employee.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.common.dto.ICommonDTO;
import com.employee.common.mapper.IBaseMapper;
import com.employee.common.service.ICommonService;

import javassist.NotFoundException;


/**
 * @author rishkumar
 *
 * @param <T> : 
 * @param <S> : 
 */
public abstract class CommonServiceImpl <T extends ICommonDTO, S extends Serializable> implements ICommonService<T> {

	
	@Override
	public T create(T dto) throws Exception {
		S entity = getMapper().convertToEntity(dto);
		if(entity != null){
			entity = getJPARepository().saveAndFlush(entity);
		}
		return getMapper().convertToDTO(entity);
	}
	
	/* (non-Javadoc)
	 * @see com.ccsp.common.service.ICommonService#readAll()
	 */
	
	@Override
	public List<T> readAll() throws NotFoundException {
		List<S> entities = getJPARepository().findAll();
		
		/**check if there are any entities in backend **/
		if(entities == null || entities.isEmpty()) {
			throw new NotFoundException("There are no Ledger Headers");
		}
		return getMapper().convertToDTOList(entities);
	}
	
	/**
	 * provides the JpaRepository to use with service.
	 * @return JPAReposity which will be used for performing DAO operations in DAO layer.
	 */
	public abstract JpaRepository<S, Long> getJPARepository();
	
	/**
	 * Provides the mapper factory to use with service.
	 * @return Mapper : Used for mapping DTO to Entity and vice-versa
	 */
	public abstract IBaseMapper<T, S> getMapper();
}
