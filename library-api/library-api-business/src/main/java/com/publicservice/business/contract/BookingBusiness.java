package com.publicservice.business.contract;

import com.publicservice.business.exception.BookingNotAllowed;
import com.publicservice.business.exception.LibraryUserNotFoundException;
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

  /**
   * Get Booking by Id.
   *
   * @param bookingKey is PK embedded id with userId and bookId.
   * @return Booking from BookingDao.
   **/
  Booking getBookingById(BookingKey bookingKey);
  /**
   * Delete Booking by Id.
   *
   * @param bookingKey is PK embedded id with userId and bookId.
   **/
  void deleteBookingById(BookingKey bookingKey);
  /**
   * Get List of Booking from specific user.
   *
   * @param libraryUser is user object.
   * @return Bookings List from BookingDao.
   **/
  List<Booking> getBookingByUserID(LibraryUser libraryUser);

  /**
   * Get position in the Booking List.
   *
   * @param bookingKey is Booking ID.
   * @return int position.
   * **/
  int myPositionInQueue(BookingKey bookingKey) throws BookingNotAllowed;

  int bookingListSize(Long bookId);

  boolean canBookABook(Book book, String username) throws LibraryUserNotFoundException;
}
