/**
 * 
 */
package com.employee.common.mapper;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import com.employee.common.dto.ICommonDTO;

/**
 * This interface provides the basic mapping to use for converting DTO
 * to Entity and vice-versa
 *@param <DTO> : DTO
 *@param <Entity> : Entity
 */
public interface IBaseMapper<DTO extends ICommonDTO, Entity extends Serializable> {
	
	/**
	 * Converts the provided entity to a DTO.
	 * @param entity : entity
	 * @return DTO : DTO
	 */
	DTO convertToDTO(Entity entity);
	
	/**
	 * Converts the list of provided entities to list of DTO's
	 * @param entities : list of entries
	 * @return DTO : List of DTO
	 */
	List<DTO> convertToDTOList(List<Entity> entities);
	
	/**
	 * Converts the provided DTO to a entity.
	 * @param dto : dto
	 * @return {@link Entity} : Entity
	 */
	Entity convertToEntity(DTO dto);
	
	/**
	 * Converts the list of provided dto's to list of entities
	 * @param dto : dto
	 * @return Entity : entity
	 */
	List<Entity> convertToEntityList(List<DTO> dto);
}
