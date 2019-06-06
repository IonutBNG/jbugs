package bug.control.bugStatusService;

import bug.bugStatus.BugStatusHandler;
import bug.converter.BugConverter;
import bug.dao.BugDao;
import bug.dto.ViewBugDto;
import utils.BugStatus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;
import java.util.stream.Collectors;

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

    @EJB
    private BugConverter bugConverter;

    /**
     * Calls the getPossibleTransitions method from BugStatusHandler
     * @param bugStatus used as a parameter in the call
     * @return result list of the possible transitions
     */
    public List<String> getPossibleTransitions(String bugStatus){
        return this.bugStatusHandler.getPossibleTransitions(BugStatus.getBugStatusByString(bugStatus))
                        .stream()
                        .map(BugStatus::getDisplayString)
                        .collect(Collectors.toList());
    }

    /**
     * Converts the view dto to entity
     * Calls the setStatus method from dao
     * @param viewBugDto used for the conversion
     */
    public void setStatus(ViewBugDto viewBugDto){
        this.bugDao.setStatus(this.bugConverter.convertViewBugDtoToBugEntity(viewBugDto));
    }

    /**
     * Generates Json
     * @param jwt used in the Json
     * @return JsonObject
     */
    private JsonObject generateJson(String jwt){
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("status", jwt)
                .build();
        return jsonObject;
    }

}
