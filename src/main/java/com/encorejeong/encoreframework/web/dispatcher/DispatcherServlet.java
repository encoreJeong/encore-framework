package com.encorejeong.encoreframework.web.dispatcher;

import com.encorejeong.encoreframework.web.handler.RequestMapping;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class DispatcherServlet extends HttpServlet {

    protected Dispatcher dispatcher;

    @Override
    public void init() {
        RequestMapping requestMapping = initHandlerMapping();
        this.dispatcher = new Dispatcher(requestMapping);
    }

    protected abstract RequestMapping initHandlerMapping();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        dispatcher.handle(req, resp);
    }
}
