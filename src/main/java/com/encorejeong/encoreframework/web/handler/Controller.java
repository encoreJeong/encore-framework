package com.encorejeong.encoreframework.web.handler;

import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    void handle(RequestParams params, HttpServletResponse response) throws IOException;
}
