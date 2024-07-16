package com.patika.userservice.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${finalcase.app.secret}")
    private String APP_SECRET ;

    @Value("${finalcase.expires.in}")
    private Long EXPIRES_IN ;

    public String generateJwtToken(Authentication auth){
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        System.out.println(System.currentTimeMillis()+"-"+ new Date().getTime());
        Date expireDate = new Date(new Date().getTime()+EXPIRES_IN);
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getUser().getId()))
                .claim("name",userDetails.getUser().getName())
                .claim("mail",userDetails.getUser().getMail())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(APP_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }


    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            return !isTokenExpired(token);
        }catch (SignatureException e){
            return false;
        }catch (MalformedJwtException e){
            return false;
        }catch (ExpiredJwtException e){
            return false;
        }catch (IllegalArgumentException e){
            return false;
        }catch (UnsupportedJwtException e){
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expretion = Jwts.parser()
                .setSigningKey(APP_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expretion.before(new Date());
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(APP_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
