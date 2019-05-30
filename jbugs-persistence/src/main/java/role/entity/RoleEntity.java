package role.entity;

import permission.entity.PermissionEntity;
import utils.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.google.common.base.Predicates.not;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
@Entity
@Table(name = "roles")
@NamedQueries(
        {
                @NamedQuery(name = RoleEntity.GET_ID, query = "Select role.id from RoleEntity role where role.type = :" + RoleEntity.TYPE),
                @NamedQuery(name = RoleEntity.GET_ALL_ROLES, query = "Select role from RoleEntity role")
        }
)
public class RoleEntity extends BaseEntity<Long> {

    public static final String TYPE = "type";
    public static final String GET_ALL_ROLES = "Role.getAllRoles";
    public static final String GET_ID = "RoleEntity.getID";

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToMany( cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "roles_permissions",
            joinColumns = @JoinColumn(name="role_id", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = @JoinColumn(name="permission_id",referencedColumnName = "id",nullable = false)
    )
    private List<PermissionEntity> permissionEntityList = new ArrayList<>();

    public RoleEntity() {
    }

    public RoleEntity(String type) {
        this.type = type;
    }

    public RoleEntity(String type, List<PermissionEntity> permissionEntityList) {
        this.type = type;
        this.permissionEntityList = permissionEntityList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PermissionEntity> getPermissionEntityList() {
        return permissionEntityList;
    }

    public void setPermissionEntityList(List<PermissionEntity> permissions) {
        this.permissionEntityList = permissions;
    }

    /**
     * Adds to the current permissions list new permissions
     * @param permissions added to the object's permission list
     */
    public void addPermissionEntityList(List<PermissionEntity> permissions) {
        this.permissionEntityList.addAll(permissions);
    }

    /**
     * Deletes from the object's list of permissions the ones from permissionEntityList
     * @param permissionEntityList deleted from the object's permission list
     */
    public void deletePermissionEntityList(List<PermissionEntity> permissionEntityList) {
        this.permissionEntityList.removeAll(permissionEntityList);
    }

    /**
     * Sets the new permission entity list and returns the object
     * Used in lambda expressions / stream().map(...)
     * @param permissionEntityList set as the permissions list
     * @return RoleEntity
     */
    public RoleEntity setPermissionsAndGetObject(List<PermissionEntity> permissionEntityList){
        this.permissionEntityList = permissionEntityList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(permissionEntityList, that.permissionEntityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, permissionEntityList);
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "type='" + type + '\'' +
                ", permissions=" + permissionEntityList +
                '}';
    }
}
