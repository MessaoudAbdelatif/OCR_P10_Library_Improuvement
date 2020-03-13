package com.publicservice.business.impl;

import static com.publicservice.business.contract.BookingValidatorBusiness.ValidationResult.SUCCESS;
import static com.publicservice.business.contract.BookingValidatorBusiness.isNotBorrowingThisBook;

import com.publicservice.business.contract.BookingBusiness;
import com.publicservice.business.exception.BookingNotAllowed;
import com.publicservice.consumer.BookingDao;
import com.publicservice.entities.Booking;
import java.util.List;
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
    List<Booking> actifBookingListSortedByCreationDate = bookingDao
        .findBookingById_BookID_IdAndClosedIsFalseOrderByDateCreation(
            newBooking.getId().getBookID().getId());
    //if newBooking.id.userid.borrows.isNotEmpty ?? need to handle ??
    if (lessThenTheDouble
            .test(actifBookingListSortedByCreationDate.size(),
                newBooking.getId().getBookID().getStock().getTotal())) {
      if (isNotBorrowingThisBook().apply(newBooking) == SUCCESS) {
        bookingDao.save(newBooking);
      } else {
        throw new BookingNotAllowed(
            "Sorry you are already borrowing this book : " + newBooking.getId().getBookID().getName() + " !!");
      }

    } else {
      throw new BookingNotAllowed(
          "Sorry our booking list for this book : " + newBooking.getId().getBookID().getName()
              + " is full !! please try later ..."
      );
    }

    return newBooking;
  }

  protected static BiPredicate<Integer, Integer> lessThenTheDouble = (theBookingList, bookTotalStock) ->
      theBookingList < bookTotalStock * 2;
}
