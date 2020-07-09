package edu.idat.eventosvirtuales.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static edu.idat.eventosvirtuales.utils.Global.AUTH_SECRET;


public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean authorized = false;
        if (jwtTokenExists(request, response)) {
            Claims claims = validateToken(request);
            if (claims.get("authorities") != null) {
                authorized = true;
                setUpSpringAuthentication(claims);
            }
        }
        if (!authorized) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private boolean jwtTokenExists(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader(HEADER);
        return authHeader != null && authHeader.startsWith(PREFIX);
    }

    private Claims validateToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parser().setSigningKey(AUTH_SECRET.getBytes()).parseClaimsJws(token).getBody();
    }

    private void setUpSpringAuthentication(Claims claims) {
        List<String> authorities = (List<String>) claims.get("authorities");
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject()
                , null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
