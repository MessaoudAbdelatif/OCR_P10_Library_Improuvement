package com.publicservice.zuulserver.business;

import com.publicservice.zuulserver.dao.LibraryRoleAccessDao;
import com.publicservice.zuulserver.dao.LibraryUserAccessDao;
import com.publicservice.zuulserver.entities.LibraryRoleAccess;
import com.publicservice.zuulserver.entities.LibraryUserAccess;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

  private LibraryRoleAccessDao libraryRoleAccess;
  private LibraryUserAccessDao libraryUserAccess;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public AccountServiceImpl(LibraryRoleAccessDao libraryRoleAccess,
      LibraryUserAccessDao libraryUserAccess,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.libraryRoleAccess = libraryRoleAccess;
    this.libraryUserAccess = libraryUserAccess;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }


  @Override
  public LibraryUserAccess saveUser(String username, String password, String confirmedPassword) {
    LibraryUserAccess user = libraryUserAccess.findByUsername(username);
    if (user != null) {
      throw new RuntimeException("User already exists");
    }
    LibraryUserAccess appUser = new LibraryUserAccess();
    appUser.setUsername(username);
    appUser.setActived(true);
    appUser.setPassword(bCryptPasswordEncoder.encode(password));
    libraryUserAccess.save(appUser);
    addRoleToUser(username, "USER");
    return appUser;
  }

  @Override
  public LibraryRoleAccess save(LibraryRoleAccess role) {
    return libraryRoleAccess.save(role);
  }

  @Override
  public LibraryUserAccess loadUserByUsername(String username) {
    return libraryUserAccess.findByUsername(username);
  }

  @Override
  public void addRoleToUser(String username, String rolename) {
    LibraryUserAccess appUser = libraryUserAccess.findByUsername(username);
    LibraryRoleAccess appRole = libraryRoleAccess.findByRoleName(rolename);
    appUser.getRoles().add(appRole);
  }
}
