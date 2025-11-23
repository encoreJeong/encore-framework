package com.example.app;

import com.encorejeong.encoreframework.web.dispatcher.DispatcherServlet;
import com.encorejeong.encoreframework.web.handler.adapter.DefaultHandlerAdapter;
import com.encorejeong.encoreframework.web.handler.adapter.HandlerAdapter;
import com.encorejeong.encoreframework.web.handler.adapter.RestHandlerAdapter;
import com.encorejeong.encoreframework.web.handler.mapping.HandlerMapping;
import com.encorejeong.encoreframework.web.handler.mapping.RestHandlerMapping;
import com.encorejeong.encoreframework.web.handler.mapping.UrlWithMethodMapping;
import com.encorejeong.encoreframework.web.view.ViewResolver;
import com.encorejeong.encoreframework.web.view.json.JsonViewResolver;
import com.encorejeong.encoreframework.web.view.text.PlainTextViewResolver;
import com.example.app.controller.defaulthandler.DefaultGETController;
import com.example.app.controller.defaulthandler.DefaultGETRestController;
import com.example.app.controller.resthandler.RestGETController;
import com.example.app.controller.resthandler.RestPOSTController;
import java.util.List;

public class SampleDispatcherServlet extends DispatcherServlet {

    @Override
    protected List<HandlerMapping> initHandlerMappings() {

        //Mapping by HTTP Method, Path Variable, Query Parameter, Url
        RestHandlerMapping restHandlerMapping = new RestHandlerMapping();
        restHandlerMapping.register("GET", "/users/{userId}", new RestGETController());
        restHandlerMapping.register("POST", "/users/{userId}", new RestPOSTController());

        //Mapping by HTTP Method, Query Parameter, Url
        UrlWithMethodMapping urlWithMethodMapping = new UrlWithMethodMapping();
        urlWithMethodMapping.register("GET", "/users", new DefaultGETController());
        urlWithMethodMapping.register("GET", "/rest/users", new DefaultGETRestController());

        return List.of(restHandlerMapping, urlWithMethodMapping);
    }

    @Override
    protected List<HandlerAdapter> initHandlerAdapters() {
        return List.of(
                new DefaultHandlerAdapter(),
                new RestHandlerAdapter()
        );
    }

    @Override
    protected List<ViewResolver> initViewResolvers() {
        return List.of(
                new JsonViewResolver(),
                new PlainTextViewResolver()
        );
    }
}
