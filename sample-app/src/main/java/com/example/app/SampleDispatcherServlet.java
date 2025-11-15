package com.example.app;

import com.encorejeong.encoreframework.web.dispatcher.DispatcherServlet;
import com.encorejeong.encoreframework.web.handler.RequestMapping;
import com.encorejeong.encoreframework.web.handler.UrlWithMethodMapping;
import com.encorejeong.encoreframework.web.handler.vo.UrlWithHttpMethod;
import com.example.app.controller.SampleGETController;

public class SampleDispatcherServlet extends DispatcherServlet {

    @Override
    protected RequestMapping initHandlerMapping() {
        UrlWithMethodMapping requestMapping = new UrlWithMethodMapping();
        requestMapping.register(new UrlWithHttpMethod("GET","/sample"), new SampleGETController());
        requestMapping.register(new UrlWithHttpMethod("POST","/sample"), new SampleGETController());
        return requestMapping;
    }

}
