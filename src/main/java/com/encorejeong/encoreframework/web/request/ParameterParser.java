package com.encorejeong.encoreframework.web.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ParameterParser {

    public static Map<String, String> getParameterMap(HttpServletRequest req) {
        Map<String, String> params = new HashMap<>();

        //Query Parameter
        parseQueryParams(req, params);

        //Path Variable
        parsePathVariable(req, params);

        return params;
    }

    private static void parseQueryParams(HttpServletRequest req, Map<String, String> params) {
        req.getParameterNames().asIterator().forEachRemaining(
                paramName ->  params.put(paramName, req.getParameter(paramName)));
    }

    private static void parsePathVariable(HttpServletRequest req, Map<String, String> params) {
        Map<String, String> pathVariables = (Map<String, String>) req.getAttribute(RequestAttributes.PATH_VARIABLES);
        if (pathVariables == null) {
            return;
        }
        params.putAll(pathVariables);
    }

}
