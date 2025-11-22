package com.encorejeong.encoreframework.web.request;

import com.encorejeong.encoreframework.web.request.vo.JsonBody;
import com.encorejeong.encoreframework.web.request.vo.RequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonParser implements BodyParser {

    private static final String CONTENT_TYPE_JSON = "application/json";

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean supports(String contentType) {
        if(contentType == null) {return false;}
        return contentType.equals(CONTENT_TYPE_JSON);
    }

    @Override
    public RequestBody parse(HttpServletRequest request) throws IOException {

        BufferedReader reader = request.getReader();
        String body = reader.lines().collect(Collectors.joining());

        return JsonBody.of(mapper.readValue(body, Map.class));
    }
}
