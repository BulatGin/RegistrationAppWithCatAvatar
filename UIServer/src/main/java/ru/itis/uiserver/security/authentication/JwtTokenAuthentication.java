package ru.itis.uiserver.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Bulat Giniyatullin
 * 23 October 2018
 */

public class JwtTokenAuthentication extends AbstractAuthenticationToken {
    private Long userId;
    private String token;

    public JwtTokenAuthentication(String token) {
        super(Collections.EMPTY_LIST);
        this.token = token;
        this.setAuthenticated(false);
    }

    public JwtTokenAuthentication(Long userId, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.userId = userId;
        this.token = token;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }
}
