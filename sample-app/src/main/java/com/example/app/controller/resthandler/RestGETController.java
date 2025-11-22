package com.example.app.controller.resthandler;

import com.encorejeong.encoreframework.web.handler.RestController;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestGETController implements RestController {

    private static final Logger log = LoggerFactory.getLogger(RestGETController.class);

    @Override
    public String handle(RequestParams params, Map<String, Object> model) throws IOException {
        log.info("[RestGETController.handle]");

        model.putAll(params.params());
        return "jsonView";
    }
}
