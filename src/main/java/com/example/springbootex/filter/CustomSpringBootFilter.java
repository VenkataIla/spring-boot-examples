package com.example.springbootex.filter;

import brave.Tracer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class CustomSpringBootFilter extends OncePerRequestFilter {


    @Autowired
    Tracer tracer;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Filter starts");
        response.setHeader("TRACE_ID",tracer.currentSpan().context().traceIdString());
         log.info("Filter ends");
         try {
             filterChain.doFilter(request, response);
         }catch (Exception e)
         {
             e.getMessage();
         }

    }
}
