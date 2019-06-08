package user.authentication;

import user.entity.UserEntity;
import utils.BaseEntity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Entity
@Table(name = "tokens")
@NamedQueries({
        @NamedQuery(name = TokenEntity.GET_VALID_TOKEN, query = "SELECT t FROM TokenEntity t WHERE t.token=:" + TokenEntity.TOKEN_PARAMETER),
        @NamedQuery(name = TokenEntity.DELETE_INVALID_TOKENS, query = "DELETE FROM TokenEntity t WHERE t.expireTime <:" + TokenEntity.EXPIRE_TIME_PARAMETER),
        @NamedQuery(name = TokenEntity.DELETE_TOKEN_LOGOUT, query = "DELETE FROM TokenEntity t WHERE t.token =:" + TokenEntity.TOKEN_PARAMETER + " AND t.user =:" + TokenEntity.USER_PARAMETER)
})
public class TokenEntity extends BaseEntity<Long> {
    public static final String GET_VALID_TOKEN = "getValidToken";
    public static final String DELETE_INVALID_TOKENS = "deleteInvalidToken";
    public static final String DELETE_TOKEN_LOGOUT = "deleteTokenLogout";

    public static final String TOKEN_PARAMETER = "tokenParameter";
    public static final String EXPIRE_TIME_PARAMETER = "expireTimeParameter";
    public static final String USER_PARAMETER = "userParameter";


    @Column(name = "token")
    private String token;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private UserEntity user;

    @Column(name = "expire_time")
    private Date expireTime;


    public TokenEntity() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
