package com.encorejeong.encoreframework.web.dispatcher;

import com.encorejeong.encoreframework.web.handler.adapter.HandlerAdapter;
import com.encorejeong.encoreframework.web.handler.mapping.HandlerMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Dispatcher {

    private final List<HandlerAdapter> handlerAdapters;
    private final List<HandlerMapping> handlerMappings;

    public Dispatcher(List<HandlerAdapter> handlerAdapters, List<HandlerMapping> handlerMappings) {
        this.handlerAdapters = handlerAdapters;
        this.handlerMappings = handlerMappings;
    }

    public void handle(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        try {

            Object handler = getHandler(request);
            if (handler == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            HandlerAdapter adapter = getHandlerAdapter(handler);
            adapter.handle(request, response, handler);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Object getHandler(HttpServletRequest request) {
        for (HandlerMapping mapping : handlerMappings) {
            Object handler = mapping.getHandler(request);
            if (handler != null) {
                return handler;
            }
        }
        return null;
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("지원되는 HandlerAdapter가 없습니다. handler=" + handler);
    }
}
