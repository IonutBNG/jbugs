package role.converter;

import role.converter.dto.RoleDto;
import role.entity.RoleEntity;

import javax.ejb.Stateless;
import java.util.ArrayList;
/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class RoleConverter {

    /**
     * Convert role entity to dto
     * @param roleEntity used for the RoleDto
     * @return new RoleDto
     */
    public RoleDto convertEntityToDto(RoleEntity roleEntity){
        return new RoleDto(roleEntity.getType(), new ArrayList<>());
    }

    /**
     * Convert role dto to entity
     * @param roleDto used for the RoleEntity
     * @return new RoleEntity
     */
    public RoleEntity convertDtoToEntity(RoleDto roleDto){
        return new RoleEntity(roleDto.getType(), new ArrayList<>());
    }
}
