package com.publicservice.consumer;

import com.publicservice.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StockDao extends JpaRepository<Stock, Long> {

}
