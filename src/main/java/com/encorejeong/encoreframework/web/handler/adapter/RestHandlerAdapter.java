package com.encorejeong.encoreframework.web.handler.adapter;

import com.encorejeong.encoreframework.web.handler.RestController;
import com.encorejeong.encoreframework.web.request.QueryParameterParser;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RestHandlerAdapter implements HandlerAdapter {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof RestController);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RestController controller = (RestController) handler;

        RequestParams params = RequestParams.of(QueryParameterParser.getParameterMap(request));

        response.setContentType("application/json;charset=UTF-8");

        Object result = controller.handle(params);
        String json = mapper.writeValueAsString(result);

        response.getWriter().write(json);
    }
}
