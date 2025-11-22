package com.encorejeong.encoreframework.web.handler;

import com.encorejeong.encoreframework.web.request.vo.RequestBody;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import java.io.IOException;
import java.util.Map;

public interface RestController {
    String handle(RequestParams params, RequestBody body, Map<String, Object> model) throws IOException;
}
