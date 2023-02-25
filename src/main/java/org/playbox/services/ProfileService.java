package org.playbox.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import org.playbox.HttpClient;
import org.playbox.Server;
import org.playbox.utils.RequestBuilder;

public class ProfileService {
    public static boolean doProfileExists(String nickname) {
        Request request = new RequestBuilder()
                .withEndpoint("/api/collections/users/records")
                .setMethod(Method.GET)
                .addQueryParam("filter", String.format("(username=\"%s\")", nickname))
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
