package permission.coverter;

import permission.coverter.dto.PermissionDto;
import permission.entity.PermissionEntity;

import javax.ejb.Stateless;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class PermissionConverter {

    /**
     * Converts entity to dto
     * @param permissionEntity used for PermissionDto
     * @return new PermissionDto
     */
    public PermissionDto convertEntityToDto(PermissionEntity permissionEntity){
        return new PermissionDto(permissionEntity.getDescription(), permissionEntity.getType());
    }

    /**
     * Converts dto to entity
     * @param permissionDto used for PermissionEntity
     * @return new PermissionEntity
     */
    public PermissionEntity convertDtoToEntity(PermissionDto permissionDto){
        return new PermissionEntity(permissionDto.getDescription(), permissionDto.getType());
    }
}
