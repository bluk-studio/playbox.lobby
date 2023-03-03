package org.playbox.services.auth;

import com.google.gson.JsonObject;

public class RegisterProfileBody {

    private String username;
    private String password;

    public RegisterProfileBody withUsername(String username) {
        this.username = username;
        return this;
    };

    public RegisterProfileBody withPassword(String password) {
        this.password = password;
        return this;
    };

    public JsonObject asJsonObject() {
        JsonObject json = new JsonObject();

        json.addProperty("username", this.username);
        json.addProperty("password", this.password);
        json.addProperty("passwordConfirm", this.password);
        json.addProperty("role", "player");

        return json;
    };

    public String asString() {
        JsonObject json = this.asJsonObject();

        return json.toString();
    };
}
