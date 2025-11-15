package com.encorejeong.encoreframework.web.handler.mapping;

import jakarta.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    Object getHandler(HttpServletRequest request);
}
