package com.publicservice.consumer;

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

  Optional<List<Booking>> findByIdBookIDAndIsClosedFalseOrderByDateCreation(Long bookID);
  Optional<List<Booking>> findBookingByIdLibraryUserID(String libraryUser);
  Optional<List<Booking>> findByIdBookIDAndIdLibraryUserIDAndIsClosedFalse(Long bookID, String libraryUser);
  Optional<List<Booking>> findBookingsByIsClosedIsTrueAndIsNotifiedFalse();
}
