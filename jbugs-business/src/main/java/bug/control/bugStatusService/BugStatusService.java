package bug.control.bugStatusService;

import bug.bugStatus.BugStatusHandler;
import bug.dao.BugDao;
import utils.BugStatus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class BugStatusService {

    @EJB
    private BugDao bugDao;

    @EJB
    private BugStatusHandler bugStatusHandler;

    /**
     * Calls the getPossibleTransitions method from BugStatusHandler
     * @param bugStatus used as a parameter in the call
     * @return result list of the possible transitions
     */
    public List<BugStatus> getPossibleTransitions(BugStatus bugStatus){
        return this.bugStatusHandler.getPossibleTransitions(bugStatus);
    }

}
