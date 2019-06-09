package bug.dto;

import bug.validation.*;
import utils.BaseDto;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
public class EditBugDto implements BaseDto {

    private Long id;

    @Title
    private String title;

    @Description
    private String description;

    @Version
    private String version;

    @Version
    private String fixedVersion;

    @Severity
    private String severity;

    @Severity
    private String status;

    @AssignedTo
    private String assignedTo;

    public EditBugDto() {
    }

    public EditBugDto(Long id, String title, String description, String version, String fixedVersion, String severity, String status, String assignedTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.version = version;
        this.fixedVersion = fixedVersion;
        this.severity = severity;
        this.status = status;
        this.assignedTo = assignedTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFixedVersion() {
        return fixedVersion;
    }

    public void setFixedVersion(String fixedVersion) {
        this.fixedVersion = fixedVersion;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
