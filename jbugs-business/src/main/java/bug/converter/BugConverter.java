package bug.converter;

import bug.dto.NewBugDto;
import bug.entity.BugEntity;

import javax.ejb.Stateless;

@Stateless
public class BugConverter {

    public BugEntity convertBugEntityToDto(NewBugDto newBugDto){

        BugEntity bugEntity = new BugEntity();

        bugEntity.setTitle(newBugDto.getTitle());
        bugEntity.setDescription(newBugDto.getDescription());
        bugEntity.setVersion(newBugDto.getVersion());
        bugEntity.setFixedVersion(newBugDto.getFixedVersion());
        bugEntity.setTargetDate(newBugDto.getTargetDate());
        bugEntity.setStatus(newBugDto.getStatus());
        bugEntity.setSeverity(newBugDto.getSeverity());
        bugEntity.setCreatedByUser(newBugDto.getCreatedByUser());
        bugEntity.setAssignedTo(newBugDto.getAssignedTo());

        return bugEntity;
    }


}
