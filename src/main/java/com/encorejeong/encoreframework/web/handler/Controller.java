package com.encorejeong.encoreframework.web.handler;

import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import java.io.IOException;
import java.util.Map;

public interface Controller {
    String handle(RequestParams params, Map<String, Object> model) throws IOException;
}
