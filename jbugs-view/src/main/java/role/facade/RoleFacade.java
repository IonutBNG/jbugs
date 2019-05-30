package role.facade;

import role.control.manageRolePermissionsService.ManageRolePermissionsService;
import role.converter.dto.RoleDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class RoleFacade {

    @EJB
    private ManageRolePermissionsService manageRolePermissionsService;

    /**
     * Returns all roles from the service
     * @return <list>RoleDto</list>
     */
    public List<RoleDto> getAllRolesForPermissionsAdd(){
        return this.manageRolePermissionsService.getAllRolesForPermissionsAdd();
    }

    /**
     * Returns all roles from the service
     * @return <list>RoleDto</list>
     */
    public List<RoleDto> getAllRolesForPermissionsDelete() {
        return this.manageRolePermissionsService.getAllRolesForPermissionsDelete();
    }

    /**
     * Calls the addPermissions method from the service
     * @param roleDtoList used in the method call
     */
    public void addPermissions(List<RoleDto> roleDtoList){
        this.manageRolePermissionsService.addPermissions(roleDtoList);
    }

    /**
     * Calls the deletePermissions method from the service
     * @param roleDtoList used in the method call
     */
    public void deletePermissions(List<RoleDto> roleDtoList) {
        this.manageRolePermissionsService.deletePermissions(roleDtoList);
    }
}
