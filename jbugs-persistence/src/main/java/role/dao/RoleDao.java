package role.dao;

import role.entity.RoleEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class RoleDao {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager entityManager;


    /**
     * Returns all the roles from the database
     * @return <list>RoleEntity</list>
     */
    public List<RoleEntity> getAllRoles(){
        return this.entityManager
                .createNamedQuery(RoleEntity.GET_ALL_ROLES, RoleEntity.class)
                .getResultList();
    }


    /**
     * Calls addPermissions method for each of the elements
     * @param roleEntityList used for the addition
     */
    public void updateRolesPermissions(List<RoleEntity> roleEntityList){
        roleEntityList.forEach(this::addPermissions);
    }


    /**
     * Adds the permissions to the roleEntity
     * @param roleEntity used for the update
     */
    private void addPermissions(RoleEntity roleEntity){
        this.entityManager
                .find(RoleEntity.class, getEntityId(roleEntity.getType()))
                .addPermissionEntityList(roleEntity.getPermissionEntityList());

    }

    /**
     * Calls deletePermissions method for each of the elements
     * @param roleEntityList used for the deletion
     */
    public void deleteRolesPermissions(List<RoleEntity> roleEntityList) {
        roleEntityList.forEach(this::deletePermissions);
    }

    /**
     * Deletes the permissions from the roleEntity
     * @param roleEntity used for the delete
     */
    private void deletePermissions(RoleEntity roleEntity) {
        this.entityManager
                .find(RoleEntity.class, getEntityId(roleEntity.getType()))
                .deletePermissionEntityList(roleEntity.getPermissionEntityList());
    }

    /**
     * Gets the id of the entity of type "type"
     * @param type used in the named query
     * @return id of type Long
     */
    private Long getEntityId(String type){
        return this.entityManager
                .createNamedQuery(RoleEntity.GET_ID, Long.class)
                .setParameter(RoleEntity.TYPE, type)
                .getSingleResult();
    }



}
