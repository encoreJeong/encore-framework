package com.encorejeong.encoreframework.web.view;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface View {
    void render(ModelAndView modelAndView, HttpServletResponse response) throws IOException;
}
