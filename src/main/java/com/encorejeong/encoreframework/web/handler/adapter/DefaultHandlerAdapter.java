package com.encorejeong.encoreframework.web.handler.adapter;

import com.encorejeong.encoreframework.web.handler.Controller;
import com.encorejeong.encoreframework.web.request.QueryParameterParser;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DefaultHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof Controller);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Controller controller = (Controller) handler;

        RequestParams params = RequestParams.of(QueryParameterParser.getParameterMap(request));

        response.setContentType("text/plain");

        String result = controller.handle(params);

        response.getWriter().write(result);

    }
}
