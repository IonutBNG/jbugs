package security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.ejb.Singleton;
import java.security.Key;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */

@Singleton
public class KeyGenerator {
    private Key key;

    public KeyGenerator() {
    }

    public Key getKey() {
        if (key == null) {
            key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        }
        return this.key;
    }
}
