package com.springwebpractice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // Логируем URI и метод запроса
        System.out.println("Запрос получен: " + request.getRequestURI() + " | Метод: " + request.getMethod());
        return true; // Продолжить обработку
    }
}
