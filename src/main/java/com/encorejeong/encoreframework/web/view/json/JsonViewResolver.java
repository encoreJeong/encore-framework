package com.encorejeong.encoreframework.web.view.json;

import com.encorejeong.encoreframework.web.view.View;
import com.encorejeong.encoreframework.web.view.ViewResolver;

public class JsonViewResolver implements ViewResolver {

    @Override
    public boolean supports(String viewName) {
        return viewName.startsWith("json");
    }

    @Override
    public View resolveViewName(String viewName) {
        return new JsonView();
    }
}
