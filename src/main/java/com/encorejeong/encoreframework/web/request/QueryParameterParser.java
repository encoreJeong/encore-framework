package com.encorejeong.encoreframework.web.request;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class QueryParameterParser {

    public static Map<String, String> getParameterMap(HttpServletRequest req) {
        Map<String, String> params = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(
                paramName ->  params.put(paramName, req.getParameter(paramName)));
        return params;
    }
}
