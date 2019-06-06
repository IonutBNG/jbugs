package security;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

public enum SecurityPermission {
    USER_MANAGEMENT("USER_MANAGEMENT"),
    PERMISSION_MANAGEMENT("PERMISSION_MANAGEMENT"),
    BUG_MANAGEMENT("BUG_MANAGEMENT"),
    BUG_CLOSE("BUG_CLOSE");

    public String getText() {
        return text;
    }

    private final String text;

    SecurityPermission(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
