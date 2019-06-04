package bug.facade;

import bug.control.NewBugService;
import bug.control.bugViewService.BugViewService;
import bug.dto.NewBugDto;
import bug.dto.ViewBugDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BugFacade {

    @EJB
    NewBugService newBugService;

    @EJB
    BugViewService bugViewService;

    public boolean addNewBug(NewBugDto newBugDto){
        System.out.println(newBugDto.toString());
        return newBugService.addNewBug(newBugDto);
    }

    public List<ViewBugDto> getAllBugs() {
        return this.bugViewService.getAllBugs();
    }

}
