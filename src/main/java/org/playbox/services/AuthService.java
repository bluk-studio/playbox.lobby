package org.playbox.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import jdk.jfr.Description;
import net.minestom.server.entity.Player;
import org.playbox.HttpClient;
import org.playbox.Server;
import org.playbox.managers.PlayerManager;
import org.playbox.services.auth.AuthorizeProfileBody;
import org.playbox.services.auth.RegisterProfileBody;
import org.playbox.utils.RequestBuilder;

import javax.management.DescriptorKey;

public class AuthService {
    public static boolean tryToAuthorizePlayer(Player player, String password) {
        boolean isAuthorized = AuthService.tryToAuthorizePlayerByUsername(player.getUsername(), password);

        PlayerManager.getByUUID(player.getUuid()).setIsAuthorized(isAuthorized);
        return isAuthorized;
    };

    public static boolean tryToRegisterPlayer(Player player, String password) {
        boolean isRegistered = AuthService.tryToRegisterPlayerByUsername(player.getUsername(), password);

        PlayerManager.getByUUID(player.getUuid()).setIsAuthorized(isRegistered);
        PlayerManager.getByUUID(player.getUuid()).setIsRegistered(isRegistered);

        return isRegistered;
    };

    private static boolean tryToRegisterPlayerByUsername(String username, String password) {
        RegisterProfileBody body = new RegisterProfileBody()
                .withUsername(username)
                .withPassword(password);

        Request request = new RequestBuilder()
                .withEndpoint("/api/collections/users/records")
                .withMethod(Method.POST)
                .withBody(body.asString())
                .asRequest();

        try {
            Response response = HttpClient.client.api(request);
            JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();

            // Trying to deserialize our body to update our ManagedPlayer
            // We're going to get these fields from our profile information:
            // - role
        } catch(Throwable error) {
            Server.LOGGER.warn(String.format("Error happened while making request %s: %s", request.toString(), error.toString()));
        };

        return false;
    };

    private static boolean tryToAuthorizePlayerByUsername(String username, String password) {
        AuthorizeProfileBody body = new AuthorizeProfileBody()
                .withUsername(username)
                .withPassword(password);

        Request request = new RequestBuilder()
                .withEndpoint("/api/collections/users/auth-with-password")
                .withMethod(Method.POST)
                .withBody(body.asString())
                .asRequest();

        try {
            Response response = HttpClient.client.api(request);
            JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();

            if (json.has("token")) {
                return true;
            };
        } catch(Throwable error) {
            Server.LOGGER.warn(String.format("Error happened while making request %s: %s", request.toString(), error.toString()));
        };

        return false;
    };
}
