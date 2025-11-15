package com.encorejeong.encoreframework.web.handler.mapping;

import com.encorejeong.encoreframework.web.handler.vo.UrlWithHttpMethod;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class UrlWithMethodMapping implements HandlerMapping {

    private final Map<UrlWithHttpMethod, Object> handlers = new HashMap<>();

    public void register(UrlWithHttpMethod urlWithHttpMethod, Object handler) {
        handlers.put(urlWithHttpMethod, handler);
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        UrlWithHttpMethod urlWithHttpMethod = new UrlWithHttpMethod(request.getMethod(),request.getRequestURI());
        return handlers.get(urlWithHttpMethod);
    }
}
