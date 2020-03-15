package com.publicservice.consumer;

import com.publicservice.entities.Book;
import com.publicservice.entities.Booking;
import com.publicservice.entities.BookingKey;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BookingDao extends JpaRepository<Booking, BookingKey> {

  Optional<List<Booking>> findByIdBookIDAndClosedFalseOrderByDateCreation(Book book);

}
