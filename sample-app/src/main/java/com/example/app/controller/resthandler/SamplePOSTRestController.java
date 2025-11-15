package com.example.app.controller.resthandler;

import com.encorejeong.encoreframework.web.handler.RestController;
import com.encorejeong.encoreframework.web.request.vo.RequestParams;
import java.io.IOException;

public class SamplePOSTRestController implements RestController {

    @Override
    public Object handle(RequestParams params) throws IOException {
        return new SampleUser(1,"postUser");
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
