package com.example.app.controller.defaulthandler;

import com.encorejeong.encoreframework.web.handler.Controller;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import java.io.IOException;
import java.util.Map;

public class DefaultPOSTController implements Controller {

    @Override
    public String handle(RequestParams params, Map<String, Object> model) throws IOException {

        StringBuilder strResponse = new StringBuilder();
        params.getKeys().forEach(key -> strResponse.append(key).append("=").append(params.getValue(key)).append("\n"));
        model.put("resposeString", "response by GET /sample controller!\n" + strResponse.toString());

        return  "textView";
    }

}
