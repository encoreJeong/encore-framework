package com.encorejeong.encoreframework.web.handler.adapter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface HandlerAdapter {

    public boolean supports(Object handler);

    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

}
