package com.encorejeong.encoreframework.web.view;

public interface ViewResolver {
    boolean supports(String viewName);
    View resolveViewName(String viewName);
}
