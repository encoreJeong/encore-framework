package com.encorejeong.encoreframework.web.handler.adapter;

import com.encorejeong.encoreframework.web.handler.RestController;
import com.encorejeong.encoreframework.web.request.ParameterParser;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import com.encorejeong.encoreframework.web.view.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class RestHandlerAdapter implements HandlerAdapter {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof RestController);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RestController controller = (RestController) handler;

        RequestParams params = RequestParams.of(ParameterParser.getParameterMap(request));
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.handle(params, model);
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.setModel(model);

        return modelAndView;
    }
}
