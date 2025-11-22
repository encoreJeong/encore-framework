package com.encorejeong.encoreframework.web.handler;

import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import java.io.IOException;

public interface RestController {
    Object handle(RequestParams params) throws IOException;
}
