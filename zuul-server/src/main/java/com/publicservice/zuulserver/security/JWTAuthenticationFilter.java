package com.publicservice.zuulserver.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicservice.zuulserver.configuration.ApplicationPropertiesConfiguration;
import com.publicservice.zuulserver.entities.LibraryUserAccess;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;
  private ObjectMapper objectMapper;
  private ApplicationPropertiesConfiguration AppProperties;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
      ObjectMapper objectMapper,
      ApplicationPropertiesConfiguration appProperties) {
    this.authenticationManager = authenticationManager;
    this.objectMapper = objectMapper;
    AppProperties = appProperties;
  }

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }


  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    try {
      LibraryUserAccess appUser = objectMapper
          .readValue(request.getInputStream(), LibraryUserAccess.class);
      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {
    User user = (User) authResult.getPrincipal();
    List<String> roles = new ArrayList<>();
    authResult.getAuthorities().forEach(a -> {
      roles.add(a.getAuthority());
    });
    String jwt = JWT.create()
        .withIssuer(request.getRequestURI())
        .withSubject(user.getUsername())
        .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
        .withExpiresAt(new Date(System.currentTimeMillis() + AppProperties.getEXPIRATION()))
        .sign(Algorithm.HMAC256(AppProperties.getSECRET()));
    response.addHeader(AppProperties.getHEADER_PREFIX(), jwt);
  }
}
