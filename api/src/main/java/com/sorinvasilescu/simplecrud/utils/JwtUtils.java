package com.sorinvasilescu.simplecrud.utils;

import com.sorinvasilescu.simplecrud.config.ConfigProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    ConfigProperties config;

    private final Key key;
    private final JwtParser jwtParser;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Autowired
    public JwtUtils(ConfigProperties config) {
        this.config = config;
        key = new SecretKeySpec(config.getJwtSecret().getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String generateJwt(Authentication auth) {
        UserDetails user = (UserDetails) auth.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + config.getJwtExpiration()*1000))
                .signWith(key)
                .compact();
    }

    public boolean verifyJwt(String authToken) {
        try {
            jwtParser.parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getUsername(String authToken) {
        return jwtParser.parseClaimsJws(authToken).getBody().getSubject();
    }
}
