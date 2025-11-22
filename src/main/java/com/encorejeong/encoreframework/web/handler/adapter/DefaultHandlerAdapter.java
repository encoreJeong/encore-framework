package com.encorejeong.encoreframework.web.handler.adapter;

import com.encorejeong.encoreframework.web.handler.Controller;
import com.encorejeong.encoreframework.web.request.ParameterParser;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import com.encorejeong.encoreframework.web.view.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DefaultHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof Controller);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Controller controller = (Controller) handler;

        RequestParams params = RequestParams.of(ParameterParser.getParameterMap(request));

        Map<String, Object> model = new HashMap<>();
        String viewName = controller.handle(params, model);
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.setModel(model);

       return modelAndView;
    }
}
