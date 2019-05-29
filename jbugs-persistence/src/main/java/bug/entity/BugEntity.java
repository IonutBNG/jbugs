package bug.entity;

import user.entity.UserEntity;
import utils.BaseEntity;

import javax.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "bugs")
public class BugEntity extends BaseEntity<Long> {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "version")
    private String version;

    @Column(name = "target_date")
    private Date targetDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "fixed_version", nullable = false)
    private String fixedVersion;

    @Column(name = "severity", nullable = false)
    private String severity;


//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn()
    @Column(name = "createdByUser")
    private int createdByUser;

    @Column(name = "assignedTo")
    private int assignedTo;


    public BugEntity() {
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

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(int createdByUser) {
        this.createdByUser = createdByUser;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugEntity bugEntity = (BugEntity) o;
        return createdByUser == bugEntity.createdByUser &&
                assignedTo == bugEntity.assignedTo &&
                Objects.equals(title, bugEntity.title) &&
                Objects.equals(description, bugEntity.description) &&
                Objects.equals(version, bugEntity.version) &&
                Objects.equals(targetDate, bugEntity.targetDate) &&
                Objects.equals(status, bugEntity.status) &&
                Objects.equals(fixedVersion, bugEntity.fixedVersion) &&
                Objects.equals(severity, bugEntity.severity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, version, targetDate, status, fixedVersion, severity, createdByUser, assignedTo);
    }

    @Override
    public String toString() {
        return "BugEntity{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", version='" + version + '\'' +
                ", targetDate=" + targetDate +
                ", status='" + status + '\'' +
                ", fixedVersion='" + fixedVersion + '\'' +
                ", severity='" + severity + '\'' +
                ", createdByUser=" + createdByUser +
                ", assignedTo=" + assignedTo +
                '}';
    }

}
