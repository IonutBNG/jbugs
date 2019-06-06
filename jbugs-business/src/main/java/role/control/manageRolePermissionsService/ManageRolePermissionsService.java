package role.control.manageRolePermissionsService;

import permission.control.permissionService.PermissionService;
import role.converter.RoleConverter;
import role.converter.dto.RoleDto;
import role.dao.RoleDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class ManageRolePermissionsService {

    @EJB
    private RoleDao roleDao;

    @EJB
    private PermissionService permissionService;

    @EJB
    private RoleConverter roleConverter;


    /**
     * Converts the role entities to dto
     * Sets the permissions to the dto and returns the result
     * @return <list>RoleDto</list>
     */
    public List<RoleDto> getAllRolesForPermissionsAdd(){
        return this.roleDao
                .getAllRoles()
                .stream()
                .map(role -> this.roleConverter
                        .convertEntityToDto(role)
                        .setPermissionsAndGetObject(this.permissionService.getEligiblePermissions(role.getPermissionEntityList())))
                .collect(Collectors.toList());
    }

    /**
     * Converts the role entities to dto
     * Sets the permissions to the dto and returns the result
     * Sets the permission
     * @return <list>RoleDto</list>
     */
    public List<RoleDto> getAllRolesForPermissionsDelete() {
        return this.roleDao
                .getAllRoles()
                .stream()
                .map(roleEntity -> this.roleConverter
                        .convertEntityToDto(roleEntity)
                        .setPermissionsAndGetObject(this.permissionService.convertEntityListToDtoList(roleEntity.getPermissionEntityList())))
                .collect(Collectors.toList());
    }

    /**
     * Converts the dto to entity and calls the update method from dao
     * @param roleDtoList used for the conversion
     */
    public void addPermissions(List<RoleDto> roleDtoList){
        this.roleDao.updateRolesPermissions(roleDtoList
                .stream()
                .map((RoleDto role) -> {
                    return this.roleConverter
                            .convertDtoToEntity(role).setPermissionsAndGetObject(this.permissionService.getAttachedPermissions(role.getPermissionDtoList()));
                })
                .collect(Collectors.toList()));
    }

    /**
     * Converts the dto to entity and calls the update method from dao
     * @param roleDtoList used for the conversion
     */
    public void deletePermissions(List<RoleDto> roleDtoList) {
        this.roleDao.deleteRolesPermissions(roleDtoList
                .stream()
                .map(roleDto -> {
                    return this.roleConverter
                            .convertDtoToEntity(roleDto).setPermissionsAndGetObject(this.permissionService.convertDtoListToEntityList(roleDto.getPermissionDtoList()));
                })
                .collect(Collectors.toList()));
    }
}
