package com.encorejeong.encoreframework.web.dispatcher;

import com.encorejeong.encoreframework.web.handler.mapping.HandlerMapping;
import com.encorejeong.encoreframework.web.handler.adapter.HandlerAdapter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public abstract class DispatcherServlet extends HttpServlet {

    protected Dispatcher dispatcher;

    @Override
    public void init() {
        List<HandlerAdapter> handlerAdapters = initHandlerAdapters();
        List<HandlerMapping> handlerMappings = initHandlerMappings();
        this.dispatcher = new Dispatcher(handlerAdapters, handlerMappings);
    }

    protected abstract List<HandlerMapping> initHandlerMappings();
    protected abstract List<HandlerAdapter> initHandlerAdapters();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        dispatcher.handle(req, resp);
    }
}
