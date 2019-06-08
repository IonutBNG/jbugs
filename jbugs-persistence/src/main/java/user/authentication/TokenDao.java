package user.authentication;

import user.dao.UserDao;
import user.entity.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.Calendar;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless
public class TokenDao {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager entityManager;

    public TokenEntity getTokenEntity(String token) {
        return entityManager.createNamedQuery(TokenEntity.GET_VALID_TOKEN, TokenEntity.class)
                .setParameter(TokenEntity.TOKEN_PARAMETER, token)
                .getResultList().get(0);

    }

    public void addToken(TokenDto tokenDto, UserEntity userEntity) {
        //todo send user entity as parameteer, in order to avoid userdao call from another dao
        TokenEntity tokenEntity = new TokenEntity();

        tokenEntity.setUser(userEntity);

        tokenEntity.setToken(tokenDto.getToken());
        tokenEntity.setExpireTime(tokenDto.getExpiryDate());
        entityManager.persist(tokenEntity);
    }

    public void cleanExpiredTokens() {
        Calendar c = Calendar.getInstance();
        Date date=new Date(c.getTimeInMillis());

        entityManager.createNamedQuery(TokenEntity.DELETE_INVALID_TOKENS)
                .setParameter(TokenEntity.EXPIRE_TIME_PARAMETER, date)
                .executeUpdate();
    }

    public void deleteTokenAtLogout(String token, UserEntity user) {
        entityManager.createNamedQuery(TokenEntity.DELETE_TOKEN_LOGOUT)
                .setParameter(TokenEntity.TOKEN_PARAMETER, token)
                .setParameter(TokenEntity.USER_PARAMETER, user)
                .executeUpdate();
    }

}
