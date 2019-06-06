package bug.entity;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public enum Severity {
    CRITICAL("Critical"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    String severityLevel;
    Severity (String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public static Severity getSeverityByString(String value){
        for (Severity severity : Severity.values()){
            if (value.equals(severity.getSeverityLevel())){
                return severity;
            }
        }
        return null;
    }

    public String getSeverityLevel(){
        return this.severityLevel;
    }

//    int severityLevel;
//    Severity (int severityLevel) {
//        this.severityLevel = severityLevel;
//    }
//
//    public static Severity getSeverityByString(String value){
//        for (Severity severity : Severity.values()){
//            if (value.equals(severity.toString())){
//                return severity;
//            }
//        }
//        return null;
//    }
}
