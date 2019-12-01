package com.publicservice.librarywebapp.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

@Component
public class CookieUtil {


  private WebApplicationPropertiesConfiguration wAPC;

  public CookieUtil(
      WebApplicationPropertiesConfiguration wAPC) {
    this.wAPC = wAPC;
  }

  public String cookieValue(HttpServletRequest httpServletRequest, String name) {
    Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
    return cookie != null ? cookie.getValue() : null;
  }

  public void clear(HttpServletResponse httpServletResponse, String name) {
    Cookie cookie = new Cookie(name, null);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(0);
    httpServletResponse.addCookie(cookie);
  }

  public String usernameFromJWT(String jwtToken) {
    Claims claims = Jwts.parser()
        .setSigningKey(wAPC.getSecret())
        .parseClaimsJws(jwtToken)
        .getBody();
    return claims.getSubject();
  }
}
