package bug.control.newBugService;

import bug.converter.BugConverter;
import bug.dao.BugDao;
import bug.dto.NewBugDto;
import bug.entity.BugEntity;
import bug.entity.Severity;
import bug.validation.BugValidator;
import exeptions.BusinessException;
import exeptions.ExceptionMessageCatalog;
import jsonfactory.JsonFactory;
import user.dao.UserDao;
import user.entity.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class NewBugService {

    @EJB
    private BugDao bugDao;

    @EJB
    private UserDao userDao;

    @EJB
    private BugConverter bugConverter;

    @EJB
    private BugValidator bugValidator;

    @EJB
    private JsonFactory jsonFactory;

    public JsonObject addNewBug(NewBugDto newBugDto){

        bugValidator.validateBean(newBugDto);

        BugEntity bugEntity;
        UserEntity userEntity;
        UserEntity userEntity2;

        bugEntity = bugConverter.convertNewBugDtoToBugEntity(newBugDto);
        userEntity = findUserByUsername(newBugDto.getAssignedTo());
        userEntity2 = findUserByUsername(newBugDto.getCreatedByUser());

        bugEntity.setCreatedByUser(userEntity2);

        bugEntity.setAssignedTo(userEntity);


        bugDao.createBug(bugEntity);

        return jsonFactory.getNewBugJson();
    }


    public UserEntity findUserByUsername(String username){
        return userDao.getUserByUsername(username);
    }


    public List<String> getSeverityValues(){
        return  Stream.of(Severity.values())
                .map(Severity::getSeverityLevel)
                .collect(Collectors.toList());
    }

}
