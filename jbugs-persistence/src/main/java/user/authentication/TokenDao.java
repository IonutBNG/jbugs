package user.authentication;

import user.dao.UserDao;

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
    @EJB
    UserDao userDao;

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager entityManager;

    public TokenEntity getTokenEntity(String token) {
        return entityManager.createNamedQuery(TokenEntity.GET_VALID_TOKEN, TokenEntity.class)
                .setParameter(TokenEntity.TOKEN_PARAMETER, token)
                .getResultList().get(0);

    }

    public void addToken(TokenDto tokenDto) {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUser(userDao.getUserByUsername(tokenDto.getUsername()));
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

}
