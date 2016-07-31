/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security.provider;

import com.ren.security.token.claim.ClaimDetail;
import com.ren.security.token.util.JwtUtil;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 *
 * @author rentius
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired
    private JwtUtil jwtUtil;    

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String tokenValue = (String) authentication.getPrincipal();
        ClaimDetail claimDetail = jwtUtil.parseToken(tokenValue);

        if (claimDetail == null || claimDetail.tokenExpired()) {
            throw new com.ren.security.authentication.AuthenticationException("JWT token is not valid");
        }        
        
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
        
        PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken = new PreAuthenticatedAuthenticationToken(claimDetail.getUserId(), claimDetail.getUsername(), Arrays.asList(authority));
        preAuthenticatedAuthenticationToken.setAuthenticated(true);
        return preAuthenticatedAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
