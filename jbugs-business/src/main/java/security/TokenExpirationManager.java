package security;

import user.authentication.TokenDao;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Singleton
public class TokenExpirationManager {
    public final static String MINUTES_IN_CLEANUP_CYCLE = "*/5";
    @EJB
    TokenDao tokenDao;

    public TokenExpirationManager() {
    }

    @Schedule(second = "*", minute = MINUTES_IN_CLEANUP_CYCLE, hour = "*", persistent = false)
    public void atSchedule() throws InterruptedException {
//        cleanExpiredTokens();
    }

    private void cleanExpiredTokens() {
        tokenDao.cleanExpiredTokens();
    }
}
