package utils;

import java.util.*;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */
public enum BugStatus {
    OPEN("Open"),
    IN_PROGRESS("In progress"),
    INFO_NEEDED("Info needed"),
    REJECTED("Rejected"),
    FIXED("Fixed"),
    CLOSED("Closed");

    private java.lang.String displayString;

    BugStatus(java.lang.String displayString) {
        this.displayString = displayString;
    }

    public java.lang.String getDisplayString(){
        return this.displayString;
    }

    public static BugStatus getBugStatusByString(String value){
        for (BugStatus status : BugStatus.values()){
            if (value.equals(status.getDisplayString())){
                return status;
            }
        }
        return null;
    }

}
