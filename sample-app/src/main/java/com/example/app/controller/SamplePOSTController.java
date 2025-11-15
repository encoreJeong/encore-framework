package com.example.app.controller;

import com.encorejeong.encoreframework.web.handler.Controller;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SamplePOSTController implements Controller {

    @Override
    public void handle(RequestParams params, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        StringBuilder strResponse = new StringBuilder();
        params.getKeys().forEach(key -> strResponse.append(key).append("=").append(params.getValue(key)).append("\n"));
        response.getWriter().write("response by POST /sample controller!\n" + strResponse);
    }
}
