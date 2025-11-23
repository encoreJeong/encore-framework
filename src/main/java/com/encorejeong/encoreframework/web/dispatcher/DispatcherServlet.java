package com.encorejeong.encoreframework.web.dispatcher;

import com.encorejeong.encoreframework.web.handler.mapping.HandlerMapping;
import com.encorejeong.encoreframework.web.handler.adapter.HandlerAdapter;
import com.encorejeong.encoreframework.web.view.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class DispatcherServlet extends HttpServlet {

    protected Dispatcher dispatcher;

    @Override
    public void init() {
        List<HandlerAdapter> handlerAdapters = initHandlerAdapters();
        List<HandlerMapping> handlerMappings = initHandlerMappings();
        List<ViewResolver> viewResolvers = initViewResolvers();
        this.dispatcher = new Dispatcher(handlerAdapters, handlerMappings, viewResolvers);
    }

    protected abstract List<HandlerMapping> initHandlerMappings();
    protected abstract List<HandlerAdapter> initHandlerAdapters();
    protected abstract List<ViewResolver> initViewResolvers();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            dispatcher.handle(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
