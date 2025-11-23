package com.encorejeong.encoreframework.web.request.vo;

import java.util.Map;

public class JsonBody implements RequestBody {

    private final Map<String, Object> body;

    public JsonBody(Map<String, Object> body) {
        this.body = body;
    }

    public static JsonBody of(Map<String, Object> jsonBody) {
        return new JsonBody(jsonBody);
    }

    @Override
    public Map<String, Object> getBody() {
        return body;
    }
}
