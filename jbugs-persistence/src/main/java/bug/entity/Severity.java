package bug.entity;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public enum Severity {
    CRITICAL(4),
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    int severityLevel;
    Severity (int severityLevel) {
        this.severityLevel = severityLevel;
    }
}
