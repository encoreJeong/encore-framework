package com.example.app.controller.defaulthandler;

import com.encorejeong.encoreframework.web.handler.RestController;
import com.encorejeong.encoreframework.web.request.vo.RequestBody;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import java.io.IOException;
import java.util.Map;

public class DefaultGETRestController implements RestController {

    @Override
    public String handle(RequestParams params, RequestBody body, Map<String, Object> model) throws IOException {
        model.put("user", new SampleUser(1, "getUser"));
        return "jsonView";
    }

    private class SampleUser {

        private long id;
        private String name;

        public SampleUser(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

}
