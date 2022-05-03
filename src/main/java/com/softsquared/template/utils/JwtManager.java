package com.softsquared.template.utils;


import com.softsquared.template.config.BaseException;
import com.softsquared.template.config.secret.Secret;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.softsquared.template.config.BaseResponseStatus.EMPTY_JWT;
import static com.softsquared.template.config.BaseResponseStatus.INVALID_JWT;


@Service
public class JwtManager {

    /*
    JWT 생성
    @param userIdx
    @return String
     */
    public static String createJwt(String id) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("id", id)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + 1 * (1000 * 60 * 60 * 24 * 365)))
                .signWith(SignatureAlgorithm.HS256, Secret.JWT_SECRET_KEY)
                .compact();
    }

    /*
    JWT에서 userIdx 추출
    @return string
    @throws BaseException
     */
    public static String getUserId() {
        try {
            //1. JWT 추출
            String accessToken = getJwt();
            if (accessToken == null || accessToken.isEmpty()) {
                throw new BaseException(EMPTY_JWT);
            }

            // 2. JWT parsing
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(Secret.JWT_SECRET_KEY)
                    .parseClaimsJws(accessToken); // Jwt가 유효하지 않다면 parseClaimsJws() 에서 JwtException이 발생한다.

            // 3. userIdx 추출
            return claims.getBody()
                    .get("id", String.class);
        } catch (JwtException e) {
            throw new BaseException(INVALID_JWT);
        }
    }

    /*
    Header에서 X-ACCESS-TOKEN 으로 JWT 추출
    @return String
     */
    private static String getJwt() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }
}
