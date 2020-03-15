package com.publicservice.business.impl;

import static com.publicservice.business.contract.BookingValidatorBusiness.ValidationResult.SUCCESS;
import static com.publicservice.business.contract.BookingValidatorBusiness.isNotBorrowingThisBook;

import com.publicservice.business.contract.BookingBusiness;
import com.publicservice.business.exception.BookingNotAllowed;
import com.publicservice.consumer.BookingDao;
import com.publicservice.entities.Book;
import com.publicservice.entities.Booking;
import java.util.function.BiPredicate;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookingBusinessImpl implements BookingBusiness {

  private final BookingDao bookingDao;

  public BookingBusinessImpl(BookingDao bookingDao) {
    this.bookingDao = bookingDao;
  }

  @Override
  public Booking createBooking(Booking newBooking) throws BookingNotAllowed {

    if (bookingListIsNotFull(newBooking.getId().getBookID())) {
      if (isNotBorrowingThisBook().apply(newBooking) == SUCCESS) {

        bookingDao.save(newBooking);   // <----- SAVE

      } else {
        throw new BookingNotAllowed(
            "Sorry you are already borrowing this book : " + newBooking.getId().getBookID()
                .getName() + " !!");
      }

    } else {
      throw new BookingNotAllowed(
          "Sorry our booking list for this book : " + newBooking.getId().getBookID().getName()
              + " is full !! please try later ..."
      );
    }

    return newBooking;
  }

  public boolean bookingListIsNotFull(Book book) {
    if (bookingDao.findBookingById_BookID_IdAndClosedIsFalseOrderByDateCreation(book.getId())
        .isPresent()) {
      return lessThenTheDouble.test(
          bookingDao.findBookingById_BookID_IdAndClosedIsFalseOrderByDateCreation(book.getId())
              .get().size(), book.getStock().getTotal());
    } else {
      return true;
    }
  }


  protected static BiPredicate<Integer, Integer> lessThenTheDouble = (theBookingList, bookTotalStock) ->
      theBookingList < bookTotalStock * 2;
}
