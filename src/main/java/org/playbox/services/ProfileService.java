package org.playbox.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import net.minestom.server.entity.Player;
import org.playbox.HttpClient;
import org.playbox.Server;
import org.playbox.managers.PlayerManager;
import org.playbox.utils.RequestBuilder;

public class ProfileService {
    public static boolean doProfileExists(Player player) {
        if (ProfileService.doProfileExistsByUsername(player.getUsername())) {
            PlayerManager.getByUUID(player.getUuid()).setIsRegistered(true);

            return true;
        } else {
            PlayerManager.getByUUID(player.getUuid()).setIsRegistered(false);

            return false;
        }
    };

    // P.S.
    // Try not to use this method very often.
    public static boolean doProfileExistsByUsername(String username) {
        Request request = new RequestBuilder()
                .withEndpoint("/api/collections/users/records")
                .withMethod(Method.GET)
                .addQueryParam("filter", String.format("(username=\"%s\")", username))
                .asRequest();

        try {
            Response response = HttpClient.client.api(request);
            JsonObject json = JsonParser.parseString(response.getBody()).getAsJsonObject();

            if (json.has("totalItems")) {
                return json.get("totalItems").getAsInt() != 0;
            };
        } catch(Throwable error) {
            Server.LOGGER.warn(String.format("Error happened while making request %s: %s", request.toString(), error.toString()));
        };

        return false;
    };
}
