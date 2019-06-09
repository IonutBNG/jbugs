package user.dao;


import com.google.common.collect.Iterables;
import user.entity.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Bungardean Tudor-Ionut
 * @since 19.1.2
 */

@Stateless
public class UserDao {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager entityManager;


    /**
     * Extracts from the database the UserEntity that corresponds to the username
     * @param username used in the query
     * @return UserEntity
     */
    public UserEntity getUserByUsername(String username){

        return Iterables.getOnlyElement(this.entityManager
                                            .createNamedQuery(UserEntity.GET_USER_BY_USERNAME, UserEntity.class)
                                            .setParameter(UserEntity.USERNAME, username)
                                            .getResultList(), null);

    }

    /**
     * Sets the new counter value
     * @param userEntity used for the query parameters set
     */
    public void setCounter(UserEntity userEntity){
        this.entityManager.createNamedQuery(UserEntity.SET_COUNTER, UserEntity.class)
                .setParameter(UserEntity.COUNTER, userEntity.getCounter())
                .setParameter(UserEntity.USERNAME, userEntity.getUsername())
                .executeUpdate();
    }

    public boolean checkIfEmailIsUsed(String email) {

       if (this.entityManager.createNamedQuery(UserEntity.CHECK_IF_EMAIL_EXISTS, UserEntity.class)
                .setParameter(UserEntity.EMAIL, email)
                .getResultList().size() >= 1) {

           return true;
       }
       return  false;

    }

    public String getUsernameByEmail(String email){
        return this.entityManager.createNamedQuery(UserEntity.GET_USERNAME_BY_EMAIL, String.class)
                .setParameter(UserEntity.EMAIL, email)
                .getSingleResult();
    }

    public boolean checkIfUsernameExists(String username) {
        if (entityManager.createNamedQuery(UserEntity.CHECK_IF_USERNAME_EXISTS, UserEntity.class)
                .setParameter(UserEntity.USERNAME, username)
                .getResultList().size() >= 1){
            return true;
        }
        return false;

    }


    public void createUser(UserEntity newUserEntity) {
        entityManager.persist(newUserEntity);
    }

    public void editUser(UserEntity userEntity) {
        //treat the case where password is not edited
        if (userEntity.getPassword() == "") {
            userEntity.setPassword(this.getUserByUsername(userEntity.getUsername()).getPassword());
        }

        //updating atomic attributes
        this.entityManager.createNamedQuery(UserEntity.EDIT_USER)
                .setParameter(UserEntity.USERNAME, userEntity.getUsername())
                .setParameter(UserEntity.FIRST_NAME, userEntity.getFirstName())
                .setParameter(UserEntity.LAST_NAME, userEntity.getLastName())
                .setParameter(UserEntity.EMAIL, userEntity.getEmail())
                .setParameter(UserEntity.MOBLE_NUMBER, userEntity.getMobileNumber())
                .setParameter(UserEntity.PASSWORD, userEntity.getPassword())
                .setParameter(UserEntity.COUNTER, userEntity.getCounter())
                .executeUpdate();

        //updating roles
        UserEntity userEntityUpdated = this.getUserByUsername(userEntity.getUsername());
        userEntityUpdated.setRoleEntityList(userEntity.getRoleEntityList());
    }

    public List<UserEntity> getAllUsers(){
        return entityManager.createNamedQuery(UserEntity.GET_ALL_USERS, UserEntity.class).getResultList();
    }




}
