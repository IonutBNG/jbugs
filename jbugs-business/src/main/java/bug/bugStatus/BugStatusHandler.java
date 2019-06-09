package bug.bugStatus;

import com.google.common.collect.ImmutableMap;
import utils.BugStatus;

import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.List;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class BugStatusHandler {

    /**
     * Maps BugStatus according to the spec
     */
    private static final ImmutableMap<BugStatus, List<BugStatus>> bugMapper =
            ImmutableMap.of(BugStatus.OPEN, Arrays.asList(BugStatus.IN_PROGRESS, BugStatus.REJECTED),
                            BugStatus.REJECTED, Arrays.asList(),
                            BugStatus.IN_PROGRESS, Arrays.asList(BugStatus.REJECTED, BugStatus.INFO_NEEDED, BugStatus.FIXED),
                            BugStatus.INFO_NEEDED, Arrays.asList(BugStatus.IN_PROGRESS),
                            BugStatus.FIXED, Arrays.asList(BugStatus.OPEN));

    /**
     * Returns the transitions that can be made from the current busStatus
     * @param bugStatus used for getting the transition list
     * @return <list>BugStatus</list>
     */
    public List<BugStatus> getPossibleTransitions(BugStatus bugStatus){
        return bugMapper.get(bugStatus);
    }

}
