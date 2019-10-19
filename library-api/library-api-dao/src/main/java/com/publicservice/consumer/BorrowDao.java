package com.publicservice.consumer;

import com.publicservice.entities.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BorrowDao extends JpaRepository<Borrow, Long> {

}
