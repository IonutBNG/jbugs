package bug.converter;

import bug.dto.NewBugDto;
import bug.dto.ViewBugDto;
import bug.entity.BugEntity;

import javax.ejb.Stateless;

@Stateless
public class BugConverter {

    public BugEntity convertNewBugDtoToBugEntity(NewBugDto newBugDto){

        BugEntity bugEntity = new BugEntity();

        bugEntity.setTitle(newBugDto.getTitle());
        bugEntity.setDescription(newBugDto.getDescription());
        bugEntity.setVersion(newBugDto.getVersion());
        bugEntity.setFixedVersion(newBugDto.getFixedVersion());
        bugEntity.setTargetDate(newBugDto.getTargetDate());
        bugEntity.setStatus(newBugDto.getStatus());

        //todo create named queries for this operations
//        bugEntity.setSeverity(newBugDto.getSeverity());
//        bugEntity.setCreatedByUser(newBugDto.getCreatedByUser());
//        bugEntity.setAssignedTo(newBugDto.getAssignedTo());

        return bugEntity;
    }

    public ViewBugDto convertBugEntityToViewBugDto(BugEntity bugEntity) {
        ViewBugDto viewBugDto = new ViewBugDto();

        viewBugDto.setTitle(bugEntity.getTitle());
        viewBugDto.setDescription(bugEntity.getDescription());
        viewBugDto.setVersion(bugEntity.getVersion());
        viewBugDto.setTargetDate(bugEntity.getTargetDate());
        viewBugDto.setStatus(bugEntity.getStatus().toString());
        viewBugDto.setFixedVersion(bugEntity.getFixedVersion());
        viewBugDto.setSeverity(bugEntity.getSeverity().toString());
        viewBugDto.setCreatedByUser(bugEntity.getCreatedByUser().getUsername());
        viewBugDto.setAssignedTo(bugEntity.getAssignedTo().getUsername());

        return viewBugDto;

    }


}
