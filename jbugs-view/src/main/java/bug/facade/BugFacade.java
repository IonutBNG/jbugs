package bug.facade;

import bug.control.editBugService.EditBugService;
import bug.control.newBugService.NewBugService;
import bug.control.bugStatusService.BugStatusService;
import bug.control.bugViewService.BugViewService;
import bug.dto.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import java.util.List;

@Stateless
public class BugFacade {

    @EJB
    NewBugService newBugService;

    @EJB
    private BugStatusService bugStatusService;

    @EJB
    BugViewService bugViewService;

    @EJB
    private EditBugService editBugService;

    public JsonObject addNewBug(NewBugDto newBugDto){
        System.out.println(newBugDto.toString());
        return newBugService.addNewBug(newBugDto);
    }

    /**
     * Calls the getPossibleTransitions from service
     * Uses as a parameter for the call its own parameter
     * Returns the result received from the method call
     * @param bugStatus used in the method call
     * @return <list>String</list>
     */
    public List<String> getPossibleTransitions(String bugStatus){
        return this.bugStatusService.getPossibleTransitions(bugStatus);
    }

    /**
     * Calls the setStatus method from service
     * Uses as a parameter for the call its own parameter
     * @param viewBugDto used in the method call
     */
    public void setStatus(ViewBugDto viewBugDto){
        this.bugStatusService.setStatus(viewBugDto);
    }

    public List<ViewBugDto> getAllBugs() {
        return this.bugViewService.getAllBugs();
    }


    public List<String> getSeverityValues(){
        return this.newBugService.getSeverityValues();
    }

    /**
     * Calls getSublist from BugViewService
     * @param bugSublistSetterDto used in the method call
     * @return <list>ViewBugDto</list>
     */
    public List<ViewBugDto> getSublist(BugSublistSetterDto bugSublistSetterDto){
        return this.bugViewService.getSublist(bugSublistSetterDto);
    }

    /**
     * Calls the editBug method from service
     * Uses as a parameter for the call its own parameter
     * Returns the result received from the method call
     * @param editBugDto used in the method call
     * @return JSonObject
     */
    public JsonObject editBug(EditBugDto editBugDto){
        return this.editBugService.editBug(editBugDto);
    }

    public BugStatisticsDto getStatistics() {
        return this.bugViewService.getStatistics();
    }

}
