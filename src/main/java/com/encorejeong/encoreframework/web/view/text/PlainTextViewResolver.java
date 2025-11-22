package com.encorejeong.encoreframework.web.view.text;

import com.encorejeong.encoreframework.web.view.View;
import com.encorejeong.encoreframework.web.view.ViewResolver;

public class PlainTextViewResolver implements ViewResolver {

    @Override
    public boolean supports(String viewName) {
        return viewName.startsWith("text");
    }

    @Override
    public View resolveViewName(String viewName) {
        return new PlainTextView();
    }
}
