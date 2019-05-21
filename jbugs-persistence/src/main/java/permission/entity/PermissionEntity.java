package permission.entity;

import utils.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Entity
@Table(name = "permissions")
public class PermissionEntity extends BaseEntity<Long> {

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
