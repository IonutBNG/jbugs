package user.dao;

import user.entity.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
     * @param username  - string
     * @return UserEntity
     */
    public UserEntity getUserByUsername(String username){
        return this.entityManager.createNamedQuery(UserEntity.GET_USER_BY_USERNAME, UserEntity.class)
                .setParameter(UserEntity.USERNAME, username).getSingleResult();
    }

    /**
     * Merges the new userEntity with the old one from the database
     * @param userEntity merged
     */
    public void setCounter(UserEntity userEntity){
        this.entityManager.merge(userEntity);
    }




}
