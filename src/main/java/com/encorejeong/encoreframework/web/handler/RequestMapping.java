package com.encorejeong.encoreframework.web.handler;

import jakarta.servlet.http.HttpServletRequest;

public interface RequestMapping {
    Controller getController(HttpServletRequest request);
}
