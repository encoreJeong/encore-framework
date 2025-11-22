package com.example.app.controller.resthandler;

import com.encorejeong.encoreframework.web.handler.RestController;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestGETController implements RestController {

    private static final Logger log = LoggerFactory.getLogger(RestGETController.class);

    @Override
    public Object handle(RequestParams params) throws IOException {
        log.info("[RestGETController.handle]");
        return params;
    }
}
