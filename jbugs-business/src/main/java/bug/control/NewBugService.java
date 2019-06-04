package bug.control;

import bug.converter.BugConverter;
import bug.dao.BugDao;
import bug.dto.NewBugDto;
import bug.entity.BugEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class NewBugService {

    @EJB
    BugDao bugDao;

    @EJB
    BugConverter bugConverter;

    public boolean addNewBug(NewBugDto newBugDto){

        BugEntity bugEntity;
        bugEntity = bugConverter.convertNewBugDtoToBugEntity(newBugDto);

        bugDao.createBug(bugEntity);

        return true;
    }




}
