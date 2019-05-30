package bug.entity;

import user.entity.UserEntity;
import utils.BaseEntity;
import utils.BugStatus;

import javax.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "bugs")
@NamedQueries({
        @NamedQuery(name = BugEntity.GET_ALL_BUGS, query = "SELECT b FROM BugEntity b")
})
public class BugEntity extends BaseEntity<Long> {
    public static final String GET_ALL_BUGS = "BugEntity.getAllBugs";

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "version")
    private String version;

    @Column(name = "target_date")
    private Date targetDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BugStatus status;

    @Column(name = "fixed_version", nullable = false)
    private String fixedVersion;

    @Enumerated(EnumType.STRING)
    @Column(name = "severity", nullable = false)
    private Severity severity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "createdByUser")
    private UserEntity createdByUser;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "assignedTo")
    private UserEntity assignedTo;


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

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public String getFixedVersion() {
        return fixedVersion;
    }

    public void setFixedVersion(String fixedVersion) {
        this.fixedVersion = fixedVersion;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public UserEntity getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(UserEntity createdByUser) {
        this.createdByUser = createdByUser;
    }

    public UserEntity getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(UserEntity assignedTo) {
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
