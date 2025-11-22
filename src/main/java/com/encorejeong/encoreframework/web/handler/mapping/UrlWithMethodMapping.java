package com.encorejeong.encoreframework.web.handler.mapping;

import com.encorejeong.encoreframework.web.handler.mapping.vo.HandlerKey;
import com.encorejeong.encoreframework.web.http.HttpMethod;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class UrlWithMethodMapping implements HandlerMapping {

    private final Map<HandlerKey, Object> handlers = new HashMap<>();

    public void register(String method, String url, Object handler) {

        HttpMethod httpMethod = HttpMethod.valueOf(method);
        PathPattern pattern = new PathPattern(url);
        HandlerKey handlerKey = new HandlerKey(httpMethod, pattern);

        handlers.put(handlerKey, handler);
    }

    @Override
    public Object getHandler(HttpServletRequest request) {

        HttpMethod httpMethod = HttpMethod.valueOf(request.getMethod());
        PathPattern pattern = new PathPattern(request.getRequestURI());
        HandlerKey handlerKey = new HandlerKey(httpMethod, pattern);

        return handlers.get(handlerKey);
    }
}
