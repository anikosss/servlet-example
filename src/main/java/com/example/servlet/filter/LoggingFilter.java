package com.example.servlet.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.ThreadContext;

import java.io.IOException;
import java.util.UUID;

@WebFilter(filterName = "logging", urlPatterns = "/*")
public class LoggingFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        final String requestId = req.getHeader("X-Request-ID");
        if (requestId != null) {
            ThreadContext.put("requestId", requestId);
        } else {
            ThreadContext.put("requestId", UUID.randomUUID().toString());
        }
        chain.doFilter(req, res);
    }
}
