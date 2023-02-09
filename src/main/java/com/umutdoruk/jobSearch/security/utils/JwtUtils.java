package com.umutdoruk.jobSearch.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;


import com.umutdoruk.jobSearch.entities.User;
import com.umutdoruk.jobSearch.exception.UnauthorizedException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final String jwtSecret= "JobSearchAPI";

    public String generateToken (User user){

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 100 * 60 * 1000))
                .withIssuer("Umut Doruk")
                .sign(algorithm);
    }

    public String getUserFromJWTToken(String jwtToken){

        return JWT.decode(jwtToken).getSubject();

    }

    public Boolean isJWTTokenValid(String jwtToken){

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        JWTVerifier verifier= JWT.require(algorithm).build();
        try {
            /*DecodedJWT decodedJWT= */
            verifier.verify(jwtToken);
            return true;
        }catch (Exception e){
            throw new UnauthorizedException(e.getMessage());
        }

    }

}
