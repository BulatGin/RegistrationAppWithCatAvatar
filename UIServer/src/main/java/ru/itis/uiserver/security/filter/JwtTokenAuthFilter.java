package ru.itis.uiserver.security.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.uiserver.security.authentication.JwtTokenAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bulat Giniyatullin
 * 23 October 2018
 */

@Component
public class JwtTokenAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        JwtTokenAuthentication authentication;
        if (header == null) {
            authentication = new JwtTokenAuthentication(null);
            authentication.setAuthenticated(false);
        } else {
            String token = header.split(" ")[1];

            authentication = new JwtTokenAuthentication(token);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
