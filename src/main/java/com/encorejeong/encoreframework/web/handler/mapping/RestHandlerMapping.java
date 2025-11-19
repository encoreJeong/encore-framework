package com.encorejeong.encoreframework.web.handler.mapping;

import com.encorejeong.encoreframework.web.handler.mapping.vo.HandlerKey;
import com.encorejeong.encoreframework.web.http.HttpMethod;
import com.encorejeong.encoreframework.web.request.RequestAttributes;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestHandlerMapping implements HandlerMapping {

    private static final Logger log = LoggerFactory.getLogger(RestHandlerMapping.class);

    private final Map<HandlerKey, Object> handlers = new HashMap<>();

    public void register(String method, String pattern, Object handler) {

        HttpMethod httpMethod = HttpMethod.valueOf(method);
        PathPattern pathPattern = new PathPattern(pattern);

        handlers.put(new HandlerKey(httpMethod, pathPattern), handler);
    }

    @Override
    public Object getHandler(HttpServletRequest request) {

        String path = request.getRequestURI();

        return mappingHandler(request, path);
    }

    private Object mappingHandler(HttpServletRequest request, String path) {
        for (Map.Entry<HandlerKey, Object> entry : handlers.entrySet()) {
            HandlerKey handlerKey = entry.getKey();
            Matcher matcher = handlerKey.pattern().matcher(path);

            if (matcher.matches()) {

                parsePathVariables(request, handlerKey, matcher);

                return entry.getValue();
            }
        }
        return null;
    }

    private static void parsePathVariables(HttpServletRequest request, HandlerKey handlerKey, Matcher matcher) {
        Map<String, String> pathVars = new HashMap<>();

        for (int i = 0; i < handlerKey.pattern().getVariableNames().size(); i++) {
            String name = handlerKey.pattern().getVariableNames().get(i);
            String value = matcher.group(i + 1);
            pathVars.put(name, value);
        }

        request.setAttribute(RequestAttributes.PATH_VARIABLES, pathVars);
    }
}
