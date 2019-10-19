package com.publicservice.consumer;

import com.publicservice.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LibraryDao extends JpaRepository<Library, Long> {

}
