package com.jskool.security.config;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;

public class WebListener implements ServletContextListener {



    private Properties properties;

    @Value("${server.servlet.session.timeout}")
    private int sessionTime;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        sce.getServletContext().setSessionTimeout(sessionTime);
    }
}
