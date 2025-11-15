package com.example.app;

import com.encorejeong.encoreframework.web.dispatcher.DispatcherServlet;
import com.encorejeong.encoreframework.web.handler.adapter.DefaultHandlerAdapter;
import com.encorejeong.encoreframework.web.handler.adapter.HandlerAdapter;
import com.encorejeong.encoreframework.web.handler.adapter.RestHandlerAdapter;
import com.encorejeong.encoreframework.web.handler.mapping.HandlerMapping;
import com.encorejeong.encoreframework.web.handler.mapping.UrlWithMethodMapping;
import com.encorejeong.encoreframework.web.handler.vo.UrlWithHttpMethod;
import com.example.app.controller.defaulthandler.SampleGETController;
import com.example.app.controller.defaulthandler.SamplePOSTController;
import com.example.app.controller.resthandler.SampleGETRestController;
import com.example.app.controller.resthandler.SamplePOSTRestController;
import java.util.List;

public class SampleDispatcherServlet extends DispatcherServlet {

    @Override
    protected List<HandlerMapping> initHandlerMappings() {
        UrlWithMethodMapping requestMapping = new UrlWithMethodMapping();
        requestMapping.register(new UrlWithHttpMethod("GET","/sample"), new SampleGETController());
        requestMapping.register(new UrlWithHttpMethod("POST","/sample"), new SamplePOSTController());

        requestMapping.register(new UrlWithHttpMethod("GET","/rest/sample"), new SampleGETRestController());
        requestMapping.register(new UrlWithHttpMethod("POST","/rest/sample"), new SamplePOSTRestController());

        return List.of(requestMapping);
    }

    @Override
    protected List<HandlerAdapter> initHandlerAdapters() {
        return List.of(
                new DefaultHandlerAdapter(),
                new RestHandlerAdapter()
        );
    }
}
