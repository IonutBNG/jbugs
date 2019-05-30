package permission.coverter.dto;

import java.util.Objects;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
public class PermissionDto {

    private String description;

    private String type;

    public PermissionDto() {
    }

    public PermissionDto(String description, String type) {
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
        PermissionDto that = (PermissionDto) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, type);
    }

    @Override
    public String toString() {
        return "PermissionDto{" +
                "description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
