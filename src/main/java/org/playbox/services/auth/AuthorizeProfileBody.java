package org.playbox.services.auth;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AuthorizeProfileBody {
    private String username;
    private String password;

    public AuthorizeProfileBody withUsername(String username) {
        this.username = username;
        return this;
    };

    public AuthorizeProfileBody withPassword(String password) {
        this.password = password;
        return this;
    };

    public JsonObject asJsonObject() {
        JsonObject json = new JsonObject();

        json.addProperty("identity", this.username);
        json.addProperty("password", this.password);

        return json;
    };

    public String asString() {
        JsonObject json = this.asJsonObject();

        return json.toString();
    };
}
