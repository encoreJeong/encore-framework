package com.encorejeong.encoreframework.web.handler.mapping.vo;

import com.encorejeong.encoreframework.web.handler.mapping.PathPattern;
import com.encorejeong.encoreframework.web.http.HttpMethod;

public record HandlerKey(HttpMethod method, PathPattern pattern) {
}
