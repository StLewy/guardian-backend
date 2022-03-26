package com.sl.guardianbackend.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;




@Component
public class JwtConfig {

    public static String secretKey;
    public static String tokenPrefix;
    public static String expirationTime;

    @Autowired
    public JwtConfig(@Value("${jwtConfig.secretKey}") String secretKey,
                     @Value("${jwtConfig.tokenPrefix}") String tokenPrefix,
                     @Value("${jwtConfig.expirationTime}") String expirationTime) {
        JwtConfig.secretKey = secretKey;
        JwtConfig.tokenPrefix = tokenPrefix;
        JwtConfig.expirationTime = expirationTime;
    }
}
