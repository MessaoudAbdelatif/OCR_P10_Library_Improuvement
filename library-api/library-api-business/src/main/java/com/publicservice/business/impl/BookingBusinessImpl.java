package com.publicservice.business.impl;

import com.publicservice.business.contract.BookingBusiness;
import com.publicservice.business.exception.BookingNotAllowed;
import com.publicservice.consumer.BookingDao;
import com.publicservice.entities.Book;
import com.publicservice.entities.Booking;
import com.publicservice.entities.LibraryUser;
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
    if (notBorrowingActually
        .test(newBooking.getId().getBookID(), newBooking.getId().getLibraryUserID())) {
      if (lessThenTheDouble.test(actifBookingListSortedByCreationDate.size(),
          newBooking.getId().getBookID().getStock()
              .getTotal())) {
        bookingDao.save(newBooking);
      } else {
        throw new BookingNotAllowed(
            "Sorry our booking list for this book : " + newBooking.getId().getBookID().getName()
                + " is full !! please try later ...");
      }

    } else {
      throw new BookingNotAllowed(
          "You are already borrowing this book : " + newBooking.getId().getBookID().getName());
    }

    return newBooking;
  }

  protected static BiPredicate<Book, LibraryUser> notBorrowingActually = (book, libraryUser) ->
      libraryUser
          .getBorrows()
          .stream()
          .noneMatch(borrow -> borrow.getBookID().getId().equals(book.getId()));

  protected static BiPredicate<Integer, Integer> lessThenTheDouble = (theBookingList, bookTotalStock) ->
      theBookingList < bookTotalStock * 2;
}
