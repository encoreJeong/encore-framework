package com.encorejeong.encoreframework.web.request.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record RequestParams(Map<String, String> params) {

    public List<String> getKeys() {
        return new ArrayList<>(params.keySet());
    }

    public String getValue(String key) {
        return params.get(key);
    }
}
