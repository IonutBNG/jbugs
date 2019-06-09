package bug.control.editBugService;

import bug.converter.BugConverter;
import bug.dao.BugDao;
import bug.dto.EditBugDto;
import bug.validation.BugValidator;
import jsonfactory.JsonFactory;
import user.control.userViewService.UserViewService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonObject;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class EditBugService {

    @EJB
    private BugDao bugDao;

    @EJB
    private BugConverter bugConverter;

    @EJB
    private BugValidator bugValidator;

    @EJB
    private UserViewService userViewService;

    @EJB
    private JsonFactory jsonFactory;

    /**
     * Validates the dto
     * Converts the dto to entity
     * Calls the editBug method from dao and uses the converted entity as parameter
     * @param editBugDto used for the conversion
     * @return JSonObject containing the success message
     */
    public JsonObject editBug(EditBugDto editBugDto){

        this.bugValidator.validateBean(editBugDto);

        this.bugDao
                .editBug(this.bugConverter
                        .convertEditBugDtoToBugEntity(editBugDto)
                        .setAssignedToReturnEntity(
                                this.userViewService.getUserByUsername(editBugDto.getAssignedTo())
                        )
                );

        return this.jsonFactory.getEditBugJSON();
    }
}
