package ru.itis.uiserver.security.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itis.uiserver.security.authentication.JwtTokenAuthentication;

import javax.security.auth.message.AuthException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bulat Giniyatullin
 * 23 October 2018
 */

@Component
public class JstTokenAuthProvider implements AuthenticationProvider {
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtTokenAuthentication jwtTokenAuthentication = (JwtTokenAuthentication) authentication;

        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws((String) jwtTokenAuthentication.getCredentials())
                    .getBody();
        } catch (MalformedJwtException | SignatureException e) {
            e.printStackTrace();
            throw new BadCredentialsException("Invalid token");
        }

        List<String> authorities = (List<String>) claims.get("roles");
        jwtTokenAuthentication = new JwtTokenAuthentication(
                Long.valueOf(claims.getSubject()),
                (String) jwtTokenAuthentication.getCredentials(),
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        return jwtTokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtTokenAuthentication.class.equals(aClass);
    }
}
