package com.springwebpractice;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application Context Initialized!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application Context Destroyed!");
    }
}