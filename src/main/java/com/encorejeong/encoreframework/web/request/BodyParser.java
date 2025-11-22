package com.encorejeong.encoreframework.web.request;

import com.encorejeong.encoreframework.web.request.vo.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface BodyParser {

    boolean supports(String contentType);
    RequestBody parse(HttpServletRequest request) throws IOException;

}
