package com.publicservice.zuulserver.dao;

import com.publicservice.zuulserver.entities.LibraryUserAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryUserAccessDao extends JpaRepository<LibraryUserAccess, Long> {
  LibraryUserAccess findByUsername(String username);
}
