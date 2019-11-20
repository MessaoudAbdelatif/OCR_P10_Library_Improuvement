package com.publicservice.zuulserver.security;

import com.publicservice.zuulserver.configuration.ApplicationPropertiesConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  private UserDetailsServiceImpl userDetailsService;
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private ApplicationPropertiesConfiguration securityParams;

  public SecurityConfig(
      UserDetailsServiceImpl userDetailsService,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userDetailsService = userDetailsService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.formLogin().loginPage("/login");
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests().antMatchers("/login/**", "/register/**", "/", "/Books").permitAll();
    http.authorizeRequests().antMatchers("/appRoles/**").hasAuthority("USER");
    http.authorizeRequests().anyRequest().authenticated();
    http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
    http.addFilterBefore(new JWTAuthorizationFiler(),
        UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // Allow eureka client to be accessed without authentication
    web.ignoring().antMatchers("/*/")//
        .antMatchers("/eureka/**")//
        .antMatchers(HttpMethod.OPTIONS, "/**"); // Request type options should be allowed.
  }
}
