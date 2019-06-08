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
    public JsonFactory() {
    }

    public JsonObject getEditUserJSON() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("status", "User edited succesfully")
                .build();
        return jsonObject;
    }

    public JsonObject getNewUserJSON() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("status", "User created succesfully")
                .build();
        return jsonObject;
    }

    public JsonObject getUserAuthentificationJSON(String jwt) {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("token", jwt)
                .build();
        return jsonObject;
    }

    public JsonObject getLogoutUserJSON() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("status", "User logged out succesfully")
                .build();
        return jsonObject;
    }

<<<<<<< HEAD
    private JsonObject getNewBugJson(){
=======
    public JsonObject getNewBugJson(){
>>>>>>> 1020c8349eb8d14ececc6f5cac233fb196363d12
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("status", "Bug added succesfully")
                .build();
        return jsonObject;
    }


<<<<<<< HEAD
=======


>>>>>>> 1020c8349eb8d14ececc6f5cac233fb196363d12
}
