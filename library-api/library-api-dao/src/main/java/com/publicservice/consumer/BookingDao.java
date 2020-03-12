package com.publicservice.consumer;

import com.publicservice.entities.Booking;
import com.publicservice.entities.BookingKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BookingDao extends JpaRepository<Booking, BookingKey> {

  List<Booking> findBookingById_BookID_IdAndClosedIsFalseOrderByDateCreation(Long bookID);

}
