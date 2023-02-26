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
import org.playbox.utils.RequestBuilder;

import javax.management.DescriptorKey;

public class AuthService {
    public static boolean tryToAuthorizePlayer(Player player, String password) {
         if (AuthService.tryToAuthorizePlayerByUsername(player.getUsername(), password)) {
             // Updating PlayerManager
             PlayerManager.getByUUID(player.getUuid()).setIsAuthorized(true);

             return true;
         } else {
             // Updating our PlayerManager
             PlayerManager.getByUUID(player.getUuid()).setIsAuthorized(false);

             return false;
         }
    };

    // P.S.
    // Try not to use this method very often.
    public static boolean tryToAuthorizePlayerByUsername(String username, String password) {
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
