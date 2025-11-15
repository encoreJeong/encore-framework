package com.example.app.controller;

import com.encorejeong.encoreframework.web.handler.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SamplePOSTController implements Controller {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.getWriter().write("Hello Encore Framework!");
    }
}
