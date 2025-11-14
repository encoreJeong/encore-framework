package com.encorejeong.encoreframework.web.dispatcher;

import com.encorejeong.encoreframework.web.handler.Controller;
import com.encorejeong.encoreframework.web.handler.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dispatcher {

    private final RequestMapping requestMapping;

    public Dispatcher(RequestMapping requestMapping) {
        this.requestMapping = requestMapping;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Controller controller = requestMapping.getController(request);

        if(controller == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        controller.handle(request, response);
    }
}
