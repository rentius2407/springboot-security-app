/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author rentius
 */
public class AuthenticationFilter extends GenericFilterBean {

    private final AuthenticationManager authenticationManager;
    private final RequestMatcher ignoreRequests;

    public AuthenticationFilter(AuthenticationManager authenticationManager, RequestMatcher ignoreRequests) {
        this.authenticationManager = authenticationManager;
        this.ignoreRequests = ignoreRequests;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = asHttp(request);
        if (!ignoreRequests.matches(httpRequest)) {
            String tokenValue = httpRequest.getHeader("X-Token");

            Authentication authenticate = authenticationManager.authenticate(new PreAuthenticatedAuthenticationToken(tokenValue, null));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }

        chain.doFilter(request, response);
    }

    private HttpServletRequest asHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

}
