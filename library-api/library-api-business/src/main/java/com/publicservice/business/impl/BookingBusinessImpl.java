package com.publicservice.business.impl;

import static com.publicservice.business.contract.BookingValidatorBusiness.ValidationResult.SUCCESS;
import static com.publicservice.business.contract.BookingValidatorBusiness.isNotBorrowingThisBook;

import com.publicservice.business.contract.BookBusiness;
import com.publicservice.business.contract.BookingBusiness;
import com.publicservice.business.contract.UserBusiness;
import com.publicservice.business.exception.BookNotFoundException;
import com.publicservice.business.exception.BookingNotAllowed;
import com.publicservice.business.exception.LibraryUserNotFoundException;
import com.publicservice.consumer.BookingDao;
import com.publicservice.entities.Book;
import com.publicservice.entities.Booking;
import com.publicservice.entities.BookingKey;
import com.publicservice.entities.Borrow;
import com.publicservice.entities.LibraryUser;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookingBusinessImpl implements BookingBusiness {

  private final BookingDao bookingDao;
  private final UserBusiness userBusiness;
  private final BookBusiness bookBusiness;


  public BookingBusinessImpl(BookingDao bookingDao, UserBusiness userBusiness,
      BookBusiness bookBusiness) {
    this.bookingDao = bookingDao;
    this.userBusiness = userBusiness;
    this.bookBusiness = bookBusiness;
  }

  @Override
  public Booking createBooking(Booking newBooking)
      throws BookingNotAllowed, LibraryUserNotFoundException, BookNotFoundException {
    List<Borrow> borrowList = userBusiness
        .checkeLibraryUserBorrowedBook(newBooking.getId().getLibraryUserID());

    LibraryUser libraryUser = userBusiness
        .oneLibraryUser(newBooking.getId().getLibraryUserID());

    Book book = bookBusiness.findOneBookById(newBooking.getId().getBookID());

    if (bookingListIsNotFull(book)) {
      newBooking.setDateCreation(Date.from(Instant.now()));

      if (borrowList.isEmpty() || borrowList == null) {

        bookingDao.save(newBooking);   // <----- SAVE

      } else if (!borrowList.isEmpty()
          && isNotBorrowingThisBook().apply(libraryUser, book) == SUCCESS) {

        bookingDao.save(newBooking);   // <----- SAVE

      } else {
        throw new BookingNotAllowed(
            "Sorry you are already borrowing this book : " + book
                .getName() + " !!");
      }
    } else {
      throw new BookingNotAllowed(
          "Sorry our booking list for this book : " + book.getName()
              + " is full !! please try later ..."
      );
    }

    return newBooking;
  }

  public boolean bookingListIsNotFull(Book book) {
    if (bookingDao.findByIdBookIDAndIsClosedFalseOrderByDateCreation(book.getId())
        .isPresent()) {
      return lessThenTheDouble.test(
          bookingDao.findByIdBookIDAndIsClosedFalseOrderByDateCreation(book.getId())
              .get().size(), book.getStock().getTotal());
    } else {
      return true;
    }
  }

  public Booking getBookingById(BookingKey bookingKey) {
    return bookingDao.getOne(bookingKey);
  }

  public List<Booking> getBookingByUserID(LibraryUser libraryUser) {
    return bookingDao.findBookingByIdLibraryUserID(libraryUser.getUsername())
        .orElse(Collections.emptyList());
  }

  public void deleteBookingById(BookingKey bookingKey) {
    bookingDao.deleteById(bookingKey);
  }

  public int myPositionInQueue(BookingKey bookingKey) throws BookingNotAllowed {
    Optional<Booking> vBooking = bookingDao.findById(bookingKey);
    Optional<List<Booking>> bookingList = bookingDao
        .findByIdBookIDAndIsClosedFalseOrderByDateCreation(bookingKey.getBookID());
    if (vBooking.isPresent() && bookingList.isPresent()) {
      return (bookingList.get().indexOf(vBooking.get())) + 1;
    } else {
      throw new BookingNotAllowed("Can't find you in the Booking List !");
    }
  }

  protected static BiPredicate<Integer, Integer> lessThenTheDouble = (theBookingList, bookTotalStock) ->
      theBookingList < bookTotalStock * 2;

}






