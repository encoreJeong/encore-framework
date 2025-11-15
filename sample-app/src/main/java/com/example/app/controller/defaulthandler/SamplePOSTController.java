package com.example.app.controller.defaulthandler;

import com.encorejeong.encoreframework.web.handler.Controller;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import java.io.IOException;

public class SamplePOSTController implements Controller {

    @Override
    public String handle(RequestParams params) throws IOException {

        StringBuilder strResponse = new StringBuilder();
        params.getKeys().forEach(key -> strResponse.append(key).append("=").append(params.getValue(key)).append("\n"));

        return "response by POST /sample controller!\n" + strResponse;
    }
}
