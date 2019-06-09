package bug.converter;

import bug.dto.EditBugDto;
import bug.dto.NewBugDto;
import bug.dto.ViewBugDto;
import bug.entity.BugEntity;
import bug.entity.Severity;
import utils.BugStatus;

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
        bugEntity.setStatus(BugStatus.getBugStatusByString("Open"));
        bugEntity.setSeverity(Severity.getSeverityByString(newBugDto.getSeverity()));

        return bugEntity;
    }

    public ViewBugDto convertBugEntityToViewBugDto(BugEntity bugEntity) {
        ViewBugDto viewBugDto = new ViewBugDto();

        viewBugDto.setId(bugEntity.getId());
        viewBugDto.setTitle(bugEntity.getTitle());
        viewBugDto.setDescription(bugEntity.getDescription());
        viewBugDto.setVersion(bugEntity.getVersion());
        viewBugDto.setTargetDate(bugEntity.getTargetDate());
        viewBugDto.setStatus(bugEntity.getStatus().getDisplayString());
        viewBugDto.setFixedVersion(bugEntity.getFixedVersion());
        viewBugDto.setSeverity(bugEntity.getSeverity().toString());
        viewBugDto.setCreatedByUser(bugEntity.getCreatedByUser().getUsername());
        viewBugDto.setAssignedTo(bugEntity.getAssignedTo().getUsername());

        return viewBugDto;

    }

    public BugEntity convertViewBugDtoToBugEntity(ViewBugDto viewBugDto){
        return new BugEntity(
                viewBugDto.getId(),
                viewBugDto.getTitle(),
                viewBugDto.getDescription(),
                viewBugDto.getVersion(),
                viewBugDto.getTargetDate(),
                BugStatus.getBugStatusByString(viewBugDto.getStatus()),
                viewBugDto.getFixedVersion(),
                Severity.getSeverityByString(viewBugDto.getSeverity()),
                //TODO solve created by user & assigned to
                null,
                null
                );
    }


    /*
    Unused or yet unknown attributes' values are set to null
     */
    public BugEntity convertEditBugDtoToBugEntity(EditBugDto editBugDto){
        return new BugEntity(
                editBugDto.getId(),
                editBugDto.getTitle(),
                editBugDto.getDescription(),
                editBugDto.getVersion(),
                null,                       //targetDate
                BugStatus.getBugStatusByString(editBugDto.getStatus()),
                editBugDto.getFixedVersion(),
                Severity.getSeverityByString(editBugDto.getSeverity()),
                null,                    //createdByUser
                null                       //assignedTo
        );


    }


}
