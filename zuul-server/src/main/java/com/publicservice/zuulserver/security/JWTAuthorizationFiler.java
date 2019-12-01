package com.publicservice.zuulserver.security;

import com.publicservice.zuulserver.configuration.ApplicationPropertiesConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;


public class JWTAuthorizationFiler extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    if (request.getMethod().equals("OPTIONS")) {
      response.setStatus(HttpServletResponse.SC_OK);
    } else {
      Cookie cookie = WebUtils.getCookie(request, "JWTtoken");
      String jwtToken = null;
      if (cookie != null) {
        jwtToken = cookie.getValue();
      }
      if (jwtToken == null)
      {
        filterChain.doFilter(request, response);
        return;
      }
      Claims claims = Jwts.parser()
          .setSigningKey(ApplicationPropertiesConfiguration.SECRET)
          .parseClaimsJws(jwtToken)
          .getBody();

      if (claims.getExpiration().before(Date.from(Instant.now()))) {
        filterChain.doFilter(request, response);
      } else {
        String username = claims.getSubject();
        ArrayList<String> roles = (ArrayList<String>)
            claims.get("roles");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(r -> {
          authorities.add(new SimpleGrantedAuthority(r));
        });
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
      }
    }
  }
}