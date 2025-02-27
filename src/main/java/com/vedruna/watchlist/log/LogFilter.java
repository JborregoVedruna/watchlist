package com.vedruna.watchlist.log;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String reqLog = "Request: " + httpRequest.getMethod() + " " + httpRequest.getRequestURI();
        if (httpRequest.getQueryString() != null) {
            reqLog += "?" + httpRequest.getQueryString();
        }
        log.info(reqLog);
        chain.doFilter(request, response);
    }
    
}
