package com.publicservice.consumer;

import com.publicservice.entities.LibraryRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleDao extends JpaRepository<LibraryRole, Long> {

  LibraryRole findByLibraryUserRole(String libraryUserRole);

}
