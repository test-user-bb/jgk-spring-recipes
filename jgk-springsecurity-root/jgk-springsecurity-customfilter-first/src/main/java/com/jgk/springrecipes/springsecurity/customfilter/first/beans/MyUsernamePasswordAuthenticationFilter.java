package com.jgk.springrecipes.springsecurity.customfilter.first.beans;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component("myUsernamePasswordAuthenticationFilter")
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    @Override
    public void setAuthenticationManager(
            AuthenticationManager authenticationManager) {
        // TODO Auto-generated method stub
        super.setAuthenticationManager(authenticationManager);
    }
    public MyUsernamePasswordAuthenticationFilter() {
        super();
        System.out.println("MyUsernamePasswordAuthenticationFilter");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        System.out.println("MyUsernamePasswordAuthenticationFilter: attemptAuthentication");

        return super.attemptAuthentication(request, response);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
                System.out.println("MyUsernamePasswordAuthenticationFilter: obtainPassword");


        return super.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
                System.out.println("MyUsernamePasswordAuthenticationFilter: obtainUsername");


        return super.obtainUsername(request);
    }

    @Override
    protected void setDetails(HttpServletRequest request,
            UsernamePasswordAuthenticationToken authRequest) {
                System.out.println("MyUsernamePasswordAuthenticationFilter: setDetails");


        super.setDetails(request, authRequest);
    }

    @Override
    public void setUsernameParameter(String usernameParameter) {
                System.out.println("MyUsernamePasswordAuthenticationFilter: setUsernameParameter");


        super.setUsernameParameter(usernameParameter);
    }

    @Override
    public void setPasswordParameter(String passwordParameter) {
                System.out.println("MyUsernamePasswordAuthenticationFilter: setPasswordParameter");


        super.setPasswordParameter(passwordParameter);
    }

    @Override
    public void setPostOnly(boolean postOnly) {
                System.out.println("MyUsernamePasswordAuthenticationFilter: ");


        super.setPostOnly(postOnly);
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request,
            HttpServletResponse response) {
                System.out.println("MyUsernamePasswordAuthenticationFilter: requiresAuthentication");


        return super.requiresAuthentication(request, response);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
                System.out.println("MyUsernamePasswordAuthenticationFilter: successfulAuthentication");


        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
                System.out.println("MyUsernamePasswordAuthenticationFilter: unsuccessfulAuthentication");


        super.unsuccessfulAuthentication(request, response, failed);
    }

    @Override
    public void setFilterProcessesUrl(String filterProcessesUrl) {
                System.out.println("MyUsernamePasswordAuthenticationFilter: setFilterProcessesUrl");


        super.setFilterProcessesUrl(filterProcessesUrl);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
                System.out.println("MyUsernamePasswordAuthenticationFilter: setMessageSource");


        super.setMessageSource(messageSource);
    }


}
