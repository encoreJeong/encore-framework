package com.example.app;

import java.io.File;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class WebServerLauncher {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        String docBase = new File(".").getAbsolutePath();
        Context context = tomcat.addContext("", docBase);

        Tomcat.addServlet(context, "sampleDispatcher", new SampleDispatcherServlet())
                .addMapping("/*");

        tomcat.start();
        tomcat.getServer().await();
    }
}
