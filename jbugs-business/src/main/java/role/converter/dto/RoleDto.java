package role.converter.dto;

import permission.coverter.dto.PermissionDto;

import java.util.List;
import java.util.Objects;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
public class RoleDto {

    private String type;

    private List<PermissionDto> permissionDtoList;

    public RoleDto() {
    }

    public RoleDto(String type, List<PermissionDto> permissionDtoList) {
        this.type = type;
        this.permissionDtoList = permissionDtoList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PermissionDto> getPermissionDtoList() {
        return permissionDtoList;
    }

    public void setPermissionDtoList(List<PermissionDto> permissionDtoList) {
        this.permissionDtoList = permissionDtoList;
    }

    /**
     * Sets the new permission dto list and returns the object
     * Used in lambda expressions / stream().map(...)
     * @param permissionDtoList set as the permissionsAllowed list
     * @return RoleEntity
     */
    public RoleDto setPermissionsAndGetObject(List<PermissionDto> permissionDtoList){
        this.permissionDtoList = permissionDtoList;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(type, roleDto.type) &&
                Objects.equals(permissionDtoList, roleDto.permissionDtoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, permissionDtoList);
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "type='" + type + '\'' +
                ", permissionDtoList=" + permissionDtoList +
                '}';
    }
}
