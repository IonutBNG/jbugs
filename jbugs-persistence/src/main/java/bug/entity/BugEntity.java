package bug.entity;

import user.entity.UserEntity;
import utils.BaseEntity;
import utils.BugStatus;

import javax.persistence.*;

import java.sql.Blob;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "bugs")
@NamedQueries({
        @NamedQuery(name = BugEntity.GET_ALL_BUGS, query = "SELECT b FROM BugEntity b"),
        @NamedQuery(name = BugEntity.SET_STATUS, query = "Update BugEntity bug set bug.status = :" + BugEntity.STATUS + " where bug.id = :" + BugEntity.ID),
        @NamedQuery(name = BugEntity.EDIT_BUG, query = "Update BugEntity bug set " +
                "bug.title = :" + BugEntity.TITLE + ", " +
                "bug.description = :" + BugEntity.DESCRIPTION + ", " +
                "bug.version = :" + BugEntity.VERSION + ", " +
                "bug.fixedVersion = :" + BugEntity.FIXED_IN_VERSION + ", " +
                "bug.severity = :" + BugEntity.SEVERITY + ", " +
                "bug.status = :" + BugEntity.STATUS + ", " +
                "bug.assignedTo = :" + BugEntity.ASSIGNED_TO + " " +
                "where bug.id = :"+BugEntity.ID),
})

public class BugEntity extends BaseEntity<Long> {
    public static final String GET_ALL_BUGS = "BugEntity.getAllBugs";
    public static final String ID = "id";
    public static final String STATUS = "status";
    public static final String SET_STATUS = "BugEntity.setStatus";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String VERSION = "version";
    public static final String FIXED_IN_VERSION = "fixedInVersion";
    public static final String SEVERITY = "severity";
    public static final String ASSIGNED_TO = "assignedTo";
    public static final String EDIT_BUG = "BugEntity.editBug";
    public static final String CLOSE_BUG = "BugEntity.closeBug";

    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "target_date", nullable = false)
    private Date targetDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BugStatus status;

    @Column(name = "fixed_version", nullable = true)
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

    @Lob
    @Column(name = "attachment", nullable = false)
    private byte[] attachment;


    public BugEntity() {
    }

    public BugEntity(Long id, String title, String description, String version, Date targetDate, BugStatus status, String fixedVersion, Severity severity, UserEntity createdByUser, UserEntity assignedTo) {
        this.setId(id);
        this.title = title;
        this.description = description;
        this.version = version;
        this.targetDate = targetDate;
        this.status = status;
        this.fixedVersion = fixedVersion;
        this.severity = severity;
        this.createdByUser = createdByUser;
        this.assignedTo = assignedTo;
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

    /**
     * Assigns to the assignedTo attribute the new value and returns the object
     * @param userEntity used in the assignment
     * @return (BugEntity)this
     */
    public BugEntity setAssignedToReturnEntity(UserEntity userEntity) {
        this.assignedTo = userEntity;
        return this;
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

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}
