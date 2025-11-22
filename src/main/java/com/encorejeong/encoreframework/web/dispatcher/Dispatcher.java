package com.encorejeong.encoreframework.web.dispatcher;

import com.encorejeong.encoreframework.web.handler.adapter.HandlerAdapter;
import com.encorejeong.encoreframework.web.handler.mapping.HandlerMapping;
import com.encorejeong.encoreframework.web.view.ModelAndView;
import com.encorejeong.encoreframework.web.view.View;
import com.encorejeong.encoreframework.web.view.ViewResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dispatcher {

    private static final Logger log = LoggerFactory.getLogger(Dispatcher.class);

    private final List<HandlerAdapter> handlerAdapters;
    private final List<HandlerMapping> handlerMappings;
    private final List<ViewResolver> viewResolvers;

    public Dispatcher(List<HandlerAdapter> handlerAdapters, List<HandlerMapping> handlerMappings,
                      List<ViewResolver> viewResolvers) {
        this.handlerAdapters = handlerAdapters;
        this.handlerMappings = handlerMappings;
        this.viewResolvers = viewResolvers;
    }

    public void handle(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        try {

            Object handler = getHandler(request);

            HandlerAdapter adapter = getHandlerAdapter(handler);

            ModelAndView modelAndView = adapter.handle(request, response, handler);

            View view = getView(modelAndView.getViewName());
            view.render(modelAndView, response);

        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            log.error(e.getMessage(), e);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            log.error(e.getMessage(), e);
        }
    }

    private Object getHandler(HttpServletRequest request) {
        for (HandlerMapping mapping : handlerMappings) {
            Object handler = mapping.getHandler(request);
            if (handler != null) {
                return handler;
            }
        }
        throw new IllegalArgumentException("No handler found for " + request.getMethod() + " " + request.getRequestURI());
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("No handlerAdapter found for " + handler);
    }

    private View getView(String viewName) {
        for (ViewResolver viewResolver : viewResolvers) {
            if (viewResolver.supports(viewName)) {
                return viewResolver.resolveViewName(viewName);
            }
        }
        throw new IllegalArgumentException("No viewResolver found for " + viewName);
    }
}
