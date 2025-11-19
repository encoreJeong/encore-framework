package com.example.app.controller.resthandler;

import com.encorejeong.encoreframework.web.handler.RestController;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import java.io.IOException;

public class RestGETController implements RestController {
    @Override
    public Object handle(RequestParams params) throws IOException {
        return params;
    }
}
