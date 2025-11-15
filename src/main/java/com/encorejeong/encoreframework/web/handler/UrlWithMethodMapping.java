package com.encorejeong.encoreframework.web.handler;

import com.encorejeong.encoreframework.web.handler.vo.UrlWithHttpMethod;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class UrlWithMethodMapping implements RequestMapping {

    private final Map<UrlWithHttpMethod, Controller> handlers = new HashMap<>();

    public void register(UrlWithHttpMethod urlWithHttpMethod, Controller controller) {
        handlers.put(urlWithHttpMethod, controller);
    }

    @Override
    public Controller getController(HttpServletRequest request) {
        UrlWithHttpMethod urlWithHttpMethod = new UrlWithHttpMethod(request.getMethod(),request.getRequestURI());
        return handlers.get(urlWithHttpMethod);
    }
}
