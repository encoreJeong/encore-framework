package com.encorejeong.encoreframework.web.handler.mapping;

import com.encorejeong.encoreframework.web.handler.mapping.vo.HandlerKey;
import com.encorejeong.encoreframework.web.http.HttpMethod;
import com.encorejeong.encoreframework.web.request.RequestAttributes;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class RestHandlerMapping implements HandlerMapping {

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
            Matcher matcher = handlerKey.pathPattern().matcher(path);

            if (matcher.matches() && isMethodMatches(request, handlerKey)) {

                parsePathVariables(request, handlerKey, matcher);

                return entry.getValue();
            }
        }
        return null;
    }

    private static boolean isMethodMatches(HttpServletRequest request, HandlerKey handlerKey) {
        return handlerKey.method().equals(HttpMethod.valueOf(request.getMethod()));
    }

    private static void parsePathVariables(HttpServletRequest request, HandlerKey handlerKey, Matcher matcher) {
        Map<String, String> pathVars = new HashMap<>();

        for (int i = 0; i < handlerKey.pathPattern().getVariableNames().size(); i++) {
            String name = handlerKey.pathPattern().getVariableNames().get(i);
            String value = matcher.group(i + 1);
            pathVars.put(name, value);
        }

        request.setAttribute(RequestAttributes.PATH_VARIABLES, pathVars);
    }
}
