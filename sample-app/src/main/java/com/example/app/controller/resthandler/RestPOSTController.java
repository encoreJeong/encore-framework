package com.example.app.controller.resthandler;

import com.encorejeong.encoreframework.web.handler.RestController;
import com.encorejeong.encoreframework.web.request.vo.RequestBody;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestPOSTController implements RestController {

    private static final Logger log = LoggerFactory.getLogger(RestPOSTController.class);

    @Override
    public String handle(RequestParams params, RequestBody body, Map<String, Object> model) throws IOException {
        log.info("[RestPOSTController.handle]");

        model.putAll(params.params());
        model.putAll(body.getBody());
        return "jsonView";
    }
}
