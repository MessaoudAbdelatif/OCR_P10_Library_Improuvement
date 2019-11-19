package com.publicservice.zuulserver.business;

import com.publicservice.zuulserver.entities.LibraryRoleAccess;
import com.publicservice.zuulserver.entities.LibraryUserAccess;

public interface AccountService {

   LibraryUserAccess saveUser(String username,String password,String confirmedPassword);
   LibraryRoleAccess save(LibraryRoleAccess role);
   LibraryUserAccess loadUserByUsername(String username);
   void addRoleToUser(String username,String rolename);
}
