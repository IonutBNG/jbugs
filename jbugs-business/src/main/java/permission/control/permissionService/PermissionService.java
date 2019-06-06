package permission.control.permissionService;

import permission.coverter.PermissionConverter;
import permission.coverter.dto.PermissionDto;
import permission.dao.PermissionDao;
import permission.entity.PermissionEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

import static org.glassfish.jersey.internal.guava.Predicates.not;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
@Stateless
public class PermissionService {

    @EJB
    private PermissionDao permissionDao;

    @EJB
    private PermissionConverter permissionConverter;


    /**
     * Converts the permission entities from dao to dto
     * @return <list>PermissionDto</list>
     */
    public List<PermissionDto> getAllPermissions(){
        return this.permissionDao
                .getAllPermissions()
                .stream()
                .map(this.permissionConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts the permission dto list to a permission entity list
     * @param permissionDtoList used for the conversion
     * @return <list>PermissionEntity</list>
     */
    public List<PermissionEntity> convertDtoListToEntityList(List<PermissionDto> permissionDtoList){
        return permissionDtoList
                .stream()
                .map(this.permissionConverter::convertDtoToEntity)
                .collect(Collectors.toList());
    }

    /**
     * Converts dto to entity
     * Calls the getAttachedPermissions method from dao
     * @param permissionDtoList used in the method call and conversion
     * @return <list>PermissionEntity</list>
     */
    public List<PermissionEntity> getAttachedPermissions(List<PermissionDto> permissionDtoList){
         return this.permissionDao
                 .getAttachedPermissions(permissionDtoList
                                            .stream()
                                            .map(this.permissionConverter::convertDtoToEntity)
                                            .collect(Collectors.toList()));
    }

    /**
     * Converts the permission entity list to a permission dto list
     * @param permissionEntityList used for the conversion
     * @return <list>PermissionDto</list>
     */
    public List<PermissionDto> convertEntityListToDtoList(List<PermissionEntity> permissionEntityList){
        return permissionEntityList
                .stream()
                .map(this.permissionConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Returns the eligible permissionsAllowed.
     * Eligible permission -> permission which isn't present in the param list, but it is persisted in the permissionsAllowed table in the database
     * Converts the permission entities to dto
     * @param permissionEntityList used to obtain the new list
     * @return <list>Permission dto</list>
     */
    public List<PermissionDto> getEligiblePermissions(List<PermissionEntity> permissionEntityList){
        return this.getUniqueElements(this.permissionDao.getAllPermissions(), permissionEntityList)
                .stream()
                .map(this.permissionConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Gets the unique elements from the primary list
     * @param primaryList the list with the needed elements
     * @param secondaryList the list used as a comparator
     * @return <list>PermissionEntity</list>
     */
    public List<PermissionEntity> getUniqueElements(List<PermissionEntity> primaryList, List<PermissionEntity> secondaryList){
        return primaryList
                .stream()
                .filter(not(secondaryList::contains))
                .collect(Collectors.toList());
    }
}
