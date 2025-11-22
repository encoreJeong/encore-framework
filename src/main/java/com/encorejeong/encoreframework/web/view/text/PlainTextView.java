package com.encorejeong.encoreframework.web.view.text;

import com.encorejeong.encoreframework.web.view.ModelAndView;
import com.encorejeong.encoreframework.web.view.View;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlainTextView implements View {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void render(ModelAndView modelAndView, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        String json = mapper.writeValueAsString(modelAndView.getModel());

        response.getWriter().write(json);
    }
}
