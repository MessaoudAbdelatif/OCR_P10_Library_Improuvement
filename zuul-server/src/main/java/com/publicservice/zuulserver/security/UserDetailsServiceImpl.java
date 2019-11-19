package com.publicservice.zuulserver.security;

import com.publicservice.zuulserver.business.AccountService;
import com.publicservice.zuulserver.entities.LibraryUserAccess;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private AccountService accountService;

  public UserDetailsServiceImpl(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LibraryUserAccess appUser = accountService.loadUserByUsername(username);
    if (appUser == null) {
      throw new UsernameNotFoundException("invalid user");
    }
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    appUser.getRoles().forEach(r -> {
      authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
    });
    return new User(appUser.getUsername(), appUser.getPassword(), authorities);
  }
}
