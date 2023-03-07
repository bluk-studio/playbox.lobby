package org.playbox.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import net.minestom.server.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.playbox.HttpClient;
import org.playbox.Server;
import org.playbox.managers.PlayerManager;
import org.playbox.managers.player.ManagedPlayer;
import org.playbox.managers.player.PlayerRole;
import org.playbox.services.auth.AuthorizeProfileBody;
import org.playbox.services.auth.RegisterProfileBody;
import org.playbox.services.auth.responses.ProfileResponse;
import org.playbox.utils.RequestBuilder;
import org.playbox.utils.errors.InvalidPayloadException;

public class AuthService {
    public static boolean tryToAuthorizePlayer(Player player, String password) {
        ManagedPlayer managedPlayer = PlayerManager.getByUUID(player.getUuid());
        ProfileResponse profile = AuthService.tryToAuthorizePlayerByUsername(player.getUsername(), password);
        boolean isAuthorized = profile != null;

        // Updating our ManagedPlayer
        managedPlayer.setIsAuthorized(isAuthorized);
        if (isAuthorized) managedPlayer.handleProfileResponse(profile);

        return isAuthorized;
    };

    public static boolean tryToRegisterPlayer(Player player, String password) {
        ManagedPlayer managedPlayer = PlayerManager.getByUUID(player.getUuid());
        ProfileResponse profile = AuthService.tryToRegisterPlayerByUsername(player.getUsername(), password);
        boolean isRegistered = profile != null;

        // Updating our ManagedPlayer
        managedPlayer.setIsRegistered(isRegistered);
        if (isRegistered) managedPlayer.handleProfileResponse(profile);

        return isRegistered;
    };

    @Nullable
    private static ProfileResponse tryToRegisterPlayerByUsername(String username, String password) {
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

            return handleProfile(json);
        } catch(Throwable error) {
            Server.LOGGER.warn(String.format("Error happened while making request %s: %s", request.toString(), error.toString()));
        };

        return null;
    };

    @Nullable
    private static ProfileResponse tryToAuthorizePlayerByUsername(String username, String password) {
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

            if (json.has("record")) {
                return handleProfile(json.get("record").getAsJsonObject());
            };
        } catch(Throwable error) {
            Server.LOGGER.warn(String.format("Error happened while making request %s: %s", request.toString(), error.toString()));
        };

        return null;
    };

    private static ProfileResponse handleProfile(JsonObject json) throws InvalidPayloadException {
        // Trying to deserialize our body to update our ManagedPlayer
        // We're going to get these fields from our profile information:
        // - id
        // - role
        if (!json.has("id") ||
                !json.has("role")
        ) {
            throw new InvalidPayloadException();
        };

        String id = json.get("id").getAsString();
        PlayerRole role = PlayerRole.fromString(json.get("role").getAsString());

        // Returning
        return new ProfileResponse(id, role);
    };
}
