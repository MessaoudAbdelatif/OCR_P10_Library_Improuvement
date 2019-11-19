package com.publicservice.zuulserver.dao;

import com.publicservice.zuulserver.entities.LibraryRoleAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRoleAccessDao extends JpaRepository<LibraryRoleAccess, Long> {
  LibraryRoleAccess findByRoleName(String roleName);
}
