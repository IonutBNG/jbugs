package jsonfactory;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
@Stateless
public class JsonFactory {
    public JsonObject getEditUserJSON() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("status", "OK")
                .build();
        return jsonObject;
    }

    public JsonObject getNewUserJSON() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("status", "OK")
                .build();
        return jsonObject;
    }

    public JsonObject getUserAuthentificationJSON(String jwt) {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("token", jwt)
                .build();
        return jsonObject;
    }
}
