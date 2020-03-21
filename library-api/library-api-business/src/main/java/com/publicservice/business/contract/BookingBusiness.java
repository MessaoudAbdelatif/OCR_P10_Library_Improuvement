package com.publicservice.business.contract;

import com.publicservice.entities.Book;
import com.publicservice.entities.Booking;
import com.publicservice.entities.BookingKey;
import com.publicservice.entities.LibraryUser;
import java.util.List;

public interface BookingBusiness {

  /**
   * When user make new booking.
   *
   * @param newBooking is the new booking entity.
   * @return new Borrow instance.
   */
  Booking createBooking(Booking newBooking) throws Exception;

  /**
   * Check if the Booking List of a Book is full or not.
   * @param book can be any book of the library.
   */
  boolean bookingListIsNotFull(Book book);
  Booking getBookingById(BookingKey bookingKey);
  List<Booking> getBookingByUserID(LibraryUser libraryUser);
}
