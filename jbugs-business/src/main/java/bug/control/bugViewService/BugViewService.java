package bug.control.bugViewService;

import bug.converter.BugConverter;
import bug.dao.BugDao;
import bug.dto.ViewBugDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless
public class BugViewService {

    @EJB
    BugDao bugDao;

    @EJB
    BugConverter bugConverter;

    public List<ViewBugDto> getAllBugs() {
        return bugDao.getAllBugs()
                .stream()
                .map(bugConverter::convertBugEntityToViewBugDto)
                .collect(Collectors.toList());
    }

}
