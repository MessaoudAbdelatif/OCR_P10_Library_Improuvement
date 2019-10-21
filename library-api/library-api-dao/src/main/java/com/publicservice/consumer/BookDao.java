package com.publicservice.consumer;

import com.publicservice.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BookDao extends JpaRepository<Book, Long> {

  Page<Book> findByNameContainsIgnoreCaseOrderByName(String keyword, Pageable pageable);
  Page<Book> findByAuthorContainsIgnoreCase(String keyword, Pageable pageable);
  Page<Book> findByPublisherContainsIgnoreCase(String keyword, Pageable pageable);
}
