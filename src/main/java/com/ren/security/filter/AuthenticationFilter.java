/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security.filter;

import com.ren.security.authentication.AuthenticationException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author rentius
 */
public class AuthenticationFilter extends GenericFilterBean {

    private final AuthenticationManager authenticationManager;
    private final RequestMatcher[] ignoreRequests;

    public AuthenticationFilter(AuthenticationManager authenticationManager, RequestMatcher[] ignoreRequests) {
        this.authenticationManager = authenticationManager;
        this.ignoreRequests = ignoreRequests;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = asHttp(request);
        if (authenticateUrl(httpRequest)) {

            try {
                String header = httpRequest.getHeader("Authorization");
                if (header == null || !header.startsWith("Bearer ")) {
                    throw new AuthenticationException("No JWT token found in request headers");
                }

                String authToken = header.substring(7);
                Authentication authenticate = authenticationManager.authenticate(new PreAuthenticatedAuthenticationToken(authToken, null));
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            } catch (AuthenticationException e) {

                ResponseStatus annotation = AnnotationUtils.getAnnotation(e.getClass(), ResponseStatus.class);

                HttpServletResponse httpResponse = asHttp(response);
                httpResponse.sendError(annotation.value().value(), annotation.reason());
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean authenticateUrl(HttpServletRequest httpRequest) {
        for (RequestMatcher ignoreRequest : ignoreRequests) {
            if (ignoreRequest.matches(httpRequest)) {
                return false;
            }
        }

        return true;
    }

    private HttpServletRequest asHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    private HttpServletResponse asHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }
}
