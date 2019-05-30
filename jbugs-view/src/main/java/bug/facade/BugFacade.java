package bug.facade;

import bug.control.NewBugService;
import bug.control.bugStatusService.BugStatusService;
import bug.control.bugViewService.BugViewService;
import bug.dto.NewBugDto;
import utils.BugStatus;
import bug.dto.ViewBugDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BugFacade {

    @EJB
    NewBugService newBugService;

    @EJB
    private BugStatusService bugStatusService;

    @EJB
    BugViewService bugViewService;

    public boolean addNewBug(NewBugDto newBugDto){
        System.out.println(newBugDto.toString());
        return newBugService.addNewBug(newBugDto);
    }

    public List<BugStatus> getPossibleTransitions(BugStatus bugStatus){
        return this.bugStatusService.getPossibleTransitions(bugStatus);
    }

    public List<ViewBugDto> getAllBugs() {
        return this.bugViewService.getAllBugs();
    }

}
