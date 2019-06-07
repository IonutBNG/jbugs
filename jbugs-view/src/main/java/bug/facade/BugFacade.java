package bug.facade;

import bug.control.newBugService.NewBugService;
import bug.control.bugStatusService.BugStatusService;
import bug.control.bugViewService.BugViewService;
import bug.dto.NewBugDto;
import bug.dto.ViewBugDto;

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

    public JsonObject addNewBug(NewBugDto newBugDto){
        System.out.println(newBugDto.toString());
        return newBugService.addNewBug(newBugDto);
    }

    public List<String> getPossibleTransitions(String bugStatus){
        return this.bugStatusService.getPossibleTransitions(bugStatus);
    }

    public void setStatus(ViewBugDto viewBugDto){
        this.bugStatusService.setStatus(viewBugDto);
    }

    public List<ViewBugDto> getAllBugs() {
        return this.bugViewService.getAllBugs();
    }


    public List<String> getSeverityValues(){
        return this.newBugService.getSeverityValues();
    }

}
