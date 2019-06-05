package bug.control;

import bug.converter.BugConverter;
import bug.dao.BugDao;
import bug.dto.NewBugDto;
import bug.entity.BugEntity;
import bug.entity.Severity;
import user.dao.UserDao;
import user.entity.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class NewBugService {

    @EJB
    BugDao bugDao;

    @EJB
    UserDao userDao;

    @EJB
    BugConverter bugConverter;

    public JsonObject addNewBug(NewBugDto newBugDto){

        BugEntity bugEntity;
        UserEntity userEntity;
        UserEntity userEntity2;

        bugEntity = bugConverter.convertNewBugDtoToBugEntity(newBugDto);
        userEntity = findUserByUsername(newBugDto.getAssignedTo());
        userEntity2 = findUserByUsername(newBugDto.getCreatedByUser());

        bugEntity.setCreatedByUser(userEntity2);
        bugEntity.setAssignedTo(userEntity);
        bugDao.createBug(bugEntity);

        return generateJson();
    }


    private JsonObject generateJson(){
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("status", "OK")
                .build();
        return jsonObject;
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
