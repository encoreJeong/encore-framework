package com.encorejeong.encoreframework.web.handler.mapping;

import com.encorejeong.encoreframework.web.handler.Controller;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class UrlMapping implements HandlerMapping {

    private final Map<String, Object> handlers = new HashMap<>();

    public void register(String path, Object handler) {
        handlers.put(path, handler);
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String path = request.getRequestURI();
        return handlers.get(path);
    }
}
