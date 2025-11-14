package com.encorejeong.encoreframework.web.handler;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestMappingImpl implements RequestMapping{

    private final Map<String, Controller> handlers = new HashMap<>();

    public void register(String path, Controller controller) {
        handlers.put(path, controller);
    }

    @Override
    public Controller getController(HttpServletRequest request) {
        String path = request.getRequestURI();
        return handlers.get(path);
    }
}
