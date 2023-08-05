package com.zheye.assignment.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.zheye.assignment.constant.BusinessException;
import com.zheye.assignment.model.User;
import lombok.extern.slf4j.Slf4j;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

import static com.zheye.assignment.constant.ResultCode.AUTHORITY_ERROR;
import static com.zheye.assignment.constant.ResultCode.TOKEN_EXPIRE;


@Slf4j
public class JwtUtil {

    private static final String tokenPassword = "123456789abc";
    public static String getToken(User user) {
        Instant dateTime = LocalDateTime.now().plusHours(1).toInstant(OffsetDateTime.now().getOffset());
        Date expireTime = Date.from(dateTime);
        String token;
        token = JWT.create()
                .withClaim("uid", user.getId())
                .withClaim("username", user.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(expireTime)
                .sign(Algorithm.HMAC256(tokenPassword));
        return token;
    }

    public static boolean verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(tokenPassword)).build();
        try {
            jwtVerifier.verify(token);
        } catch (TokenExpiredException e) {
            log.warn("token expired: {}", token);
            throw new BusinessException(TOKEN_EXPIRE);
        } catch (JWTVerificationException e) {
            log.warn("JWT Verification error: {}", token);
            throw new BusinessException(AUTHORITY_ERROR);
        }
        return true;
    }

}
