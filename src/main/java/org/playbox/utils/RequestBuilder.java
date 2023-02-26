package org.playbox.utils;

import com.sendgrid.Method;
import com.sendgrid.Request;
import org.playbox.HttpClient;

public class RequestBuilder {
    private final Request request;

    public RequestBuilder() {
        this.request = new Request();
        this.request.setBaseUri(HttpClient.baseUrl);
    };

    public RequestBuilder withEndpoint(String endpoint) {
        this.request.setEndpoint(endpoint);
        return this;
    };

    public RequestBuilder addQueryParam(String key, String value) {
        this.request.addQueryParam(key, value);
        return this;
    };

    public RequestBuilder withMethod(Method method) {
        this.request.setMethod(method);
        return this;
    };

    public RequestBuilder withBody(String body) {
        this.request.setBody(body);
        return this;
    };

    public Request asRequest() {
        return this.request;
    };
}
