package bug.dto;

import java.util.Objects;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
public class BugSublistSetterDto {

    private String field;

    private String value;

    private int pageNumber;

    private int pageSize;

    public BugSublistSetterDto() {
    }

    public BugSublistSetterDto(String field, String value, int pageNumber, int pageSize) {
        this.field = field;
        this.value = value;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugSublistSetterDto that = (BugSublistSetterDto) o;
        return pageNumber == that.pageNumber &&
                pageSize == that.pageSize &&
                Objects.equals(field, that.field) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, value, pageNumber, pageSize);
    }
}
