package com.example.app;

import com.encorejeong.encoreframework.web.dispatcher.DispatcherServlet;
import com.encorejeong.encoreframework.web.handler.RequestMapping;
import com.encorejeong.encoreframework.web.handler.RequestMappingImpl;
import com.example.app.controller.SampleController;

public class SampleDispatcherServlet extends DispatcherServlet {

    @Override
    protected RequestMapping initHandlerMapping() {
        RequestMappingImpl requestMapping = new RequestMappingImpl();
        requestMapping.register("/sample", new SampleController());
        return requestMapping;
    }

}
