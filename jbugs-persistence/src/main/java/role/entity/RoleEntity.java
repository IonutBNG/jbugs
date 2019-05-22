package role.entity;

import permission.entity.PermissionEntity;
import utils.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity<Long> {

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "roles_permissions",
            joinColumns = @JoinColumn(name="role_id", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = @JoinColumn(name="permission_id",referencedColumnName = "id",nullable = false)
    )
    private List<PermissionEntity> permissionEntityList = new ArrayList<>();

    public RoleEntity() {
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
