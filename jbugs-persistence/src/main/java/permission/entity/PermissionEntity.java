package permission.entity;

import utils.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
@Entity
@Table(name = "permissions")
@NamedQueries(
        {
                @NamedQuery(name = PermissionEntity.GET_ALL_PERMISSIONS, query = "Select permission from PermissionEntity permission"),
                @NamedQuery(name = PermissionEntity.GET_ID, query = "Select permission.id from PermissionEntity permission where permission.type = :" + PermissionEntity.TYPE)
        }
)
public class PermissionEntity extends BaseEntity<Long> {

    public static final String GET_ALL_PERMISSIONS = "Permission.getAllPermissions";
    public static final String TYPE = "type";
    public static final String GET_ID = "PermissionEntity.getID";

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    public PermissionEntity() {
    }

    public PermissionEntity(String description, String type) {
        this.description = description;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionEntity that = (PermissionEntity) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, type);
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
