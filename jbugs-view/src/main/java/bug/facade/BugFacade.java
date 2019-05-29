package bug.facade;

import bug.control.NewBugService;
import bug.dto.NewBugDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class BugFacade {

    @EJB
    NewBugService newBugService;

    public boolean addNewBug(NewBugDto newBugDto){
        System.out.println(newBugDto.toString());
        return newBugService.addNewBug(newBugDto);
    }

}
