package com.restpractice.topic3;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomCorsFilter implements Filter {

    private static final String ALLOWED_ORIGIN = "https://secure-bank.ru";
    private static final String ALLOWED_METHODS = "GET, POST, PATCH";
    private static final String ALLOWED_HEADERS = "Content-Type, Authorization, X-Request-ID";
    private static final String SECURITY_POLICY = "Strict";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setHeader("Access-Control-Allow-Origin", ALLOWED_ORIGIN);

        httpResponse.setHeader("Access-Control-Allow-Methods", ALLOWED_METHODS);

        httpResponse.setHeader("Access-Control-Allow-Headers", ALLOWED_HEADERS);

        httpResponse.setHeader("X-Security-Policy", SECURITY_POLICY);

        httpResponse.setHeader("Vary", "Origin");

        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        chain.doFilter(request, response);
    }

}
