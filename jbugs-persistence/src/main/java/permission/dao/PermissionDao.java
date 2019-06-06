package permission.dao;

import permission.entity.PermissionEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
@Stateless
public class PermissionDao {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager entityManager;


    /**
     * Returns all permissions from the database
     * @return <list>PermissionEntity</list>
     */
    public List<PermissionEntity> getAllPermissions(){
        return this.entityManager
                .createNamedQuery(PermissionEntity.GET_ALL_PERMISSIONS, PermissionEntity.class)
                .getResultList();
    }

    /**
     * Calls the attachPermission method for every element of the list parameter
     * Returns a list of attached permissions
     * @param permissionEntityList used for the method call
     * @return <list>PermissionEntity</list>
     */
    public List<PermissionEntity> getAttachedPermissions(List<PermissionEntity> permissionEntityList){
        return permissionEntityList
                .stream()
                .map(this::attachPermission)
                .collect(Collectors.toList());
    }

    /**
     * Returns an attacked version of the parameter used in the method call
     * @param permissionEntity used for the attached version finding
     * @return attached PermissionEntity
     */
    public PermissionEntity attachPermission(PermissionEntity permissionEntity){
        Long id = this.entityManager
                .createNamedQuery(PermissionEntity.GET_ID, Long.class)
                .setParameter(PermissionEntity.TYPE, permissionEntity.getType())
                .getSingleResult();
        return this.entityManager
                .find(PermissionEntity.class, id);
    }
}
