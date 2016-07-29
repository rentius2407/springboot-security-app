/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ren.security.token.util;

import com.ren.security.token.claim.ClaimDetail;
import com.ren.security.token.claim.ExpireDate;
import com.ren.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author cp332918
 */
@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns
     * User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user
     * properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token
     * is invalid.
     */
    public ClaimDetail parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            User user = new User();
            user.setUsername(body.getSubject());
            user.setId(Long.parseLong((String) body.get("userId")));
            user.setRole((String) body.get("role"));

            Long expireTime = (Long) body.get("expire");

            return new ClaimDetail(
                    user,
                    new ExpireDate().time(expireTime)
            );

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role
     * as additional claims. These properties are taken from the specified User
     * object. Tokens validity is infinite.
     *
     * @param claimDetail the ClaimDetail for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(ClaimDetail claimDetail) {
        Claims claims = Jwts.claims().setSubject(claimDetail.getUsername());
        claims.put("userId", claimDetail.getUserId() + "");
        claims.put("role", claimDetail.getRole());
        claims.put("expire", claimDetail.getExpireTime());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
