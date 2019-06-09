package bug.control.bugViewService;

import bug.converter.BugConverter;
import bug.dao.BugDao;
import bug.dto.BugStatisticsDto;
import bug.dto.BugSublistSetterDto;
import bug.dto.ViewBugDto;
import bug.entity.BugEntity;
import utils.BugStatus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.Comparator;
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

    /**
     * Calls the getSublist method from dao
     * Converts the returned list from one of entities to one of dto
     * @param bugSublistSetterDto used for operations executed on the database bugs list
     * @return <list>ViewBugDto</list>
     */
    public List<ViewBugDto> getSublist(BugSublistSetterDto bugSublistSetterDto){
        return this.bugDao
                .getSublist(
                        bugSublistSetterDto.getField(),
                        bugSublistSetterDto.getValue(),
                        getStartingItem(bugSublistSetterDto.getPageNumber(), bugSublistSetterDto.getPageSize()),
                        bugSublistSetterDto.getPageSize())
                .stream()
                .map(this.bugConverter::convertBugEntityToViewBugDto)
                .collect(Collectors.toList());
    }

    /**
     * Computes the index of the starting item based on the page number and page size
     * @param pageNumber used for the
     * @param pageSize
     * @return
     */
    private int getStartingItem(int pageNumber, int pageSize){
        return pageNumber*pageSize;
    }
    public BugStatisticsDto getStatistics() {
        BugStatisticsDto bugStatisticsDto = new BugStatisticsDto();
        List<BugEntity> allBugs = bugDao.getAllBugs();

        bugStatisticsDto.setTotalBugs(allBugs.size());

        bugStatisticsDto.setTotalOpen(Math.toIntExact(
                allBugs.stream().filter(p -> ( p.getStatus() == BugStatus.OPEN)).count() ));

        bugStatisticsDto.setTotalInProgress(Math.toIntExact(
                allBugs.stream().filter(p -> ( p.getStatus() == BugStatus.IN_PROGRESS)).count() ));

        bugStatisticsDto.setTotalRejected(Math.toIntExact(
                allBugs.stream().filter(p -> ( p.getStatus() == BugStatus.REJECTED)).count() ));

        bugStatisticsDto.setTotalInfoNeeded(Math.toIntExact(
                allBugs.stream().filter(p -> ( p.getStatus() == BugStatus.INFO_NEEDED)).count() ));

        bugStatisticsDto.setTotalFixed(Math.toIntExact(
                allBugs.stream().filter(p -> ( p.getStatus() == BugStatus.FIXED)).count() ));

        bugStatisticsDto.setTotalClosed(Math.toIntExact(
                allBugs.stream().filter(p -> ( p.getStatus() == BugStatus.CLOSED)).count() ));

        return bugStatisticsDto;

    }

}
