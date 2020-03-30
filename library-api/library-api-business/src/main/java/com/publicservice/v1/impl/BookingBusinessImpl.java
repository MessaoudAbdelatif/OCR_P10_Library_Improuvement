package com.publicservice.v1.impl;

import static com.publicservice.v1.contract.BookingValidatorBusiness.ValidationResult.SUCCESS;
import static com.publicservice.v1.contract.BookingValidatorBusiness.isNotBorrowingThisBook;

import com.publicservice.v1.contract.BookBusiness;
import com.publicservice.v1.contract.BookingBusiness;
import com.publicservice.v1.contract.UserBusiness;
import com.publicservice.v1.exception.BookNotFoundException;
import com.publicservice.v1.exception.BookingNotAllowed;
import com.publicservice.v1.exception.LibraryUserNotFoundException;
import com.publicservice.consumer.BookingDao;
import com.publicservice.entities.Book;
import com.publicservice.entities.Booking;
import com.publicservice.entities.BookingKey;
import com.publicservice.entities.Borrow;
import com.publicservice.entities.LibraryUser;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
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

  public boolean canBookABook(Book book, String username) throws LibraryUserNotFoundException {
    List<Borrow> borrows = userBusiness.checkeLibraryUserBorrowedBook(username);
    boolean anyMatch = borrows.stream()
        .noneMatch(borrow -> borrow.getBookID().equals(book));
    return bookingListIsNotFull(book) && anyMatch;
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

  public int bookingListSize(Long bookId) {
    boolean present = bookingDao
        .findByIdBookIDAndIsClosedFalseOrderByDateCreation(bookId).isPresent();
    if (present) {
      return bookingDao
          .findByIdBookIDAndIsClosedFalseOrderByDateCreation(bookId).get().size();
    } else {
      return 0;
    }

  }

  public Booking theHeadOfList(Long bookId) throws BookNotFoundException {
    Optional<List<Booking>> orderByDateCreation = bookingDao
        .findByIdBookIDAndIsClosedFalseOrderByDateCreation(bookId);
    if (orderByDateCreation.isPresent()) {
      List<Booking> bookings = orderByDateCreation.get();
      return bookings.get(0);
    } else {
      throw new BookNotFoundException("There is not booking list for this book !!");
    }
  }

  public Optional<List<Booking>> allBookingsClosedNotNotified() {
    return bookingDao.findBookingsByIsClosedIsTrueAndIsNotifiedFalse();
  }

  @Override
  public void updateBookingInfo(Booking booking) {
    bookingDao.save(booking);
  }

  @Scheduled(cron = "0 * * * * ?")
  public void deletingOverTwoDaysClosed() {
    log.warn(
        "********** EVERY 1H I REMOVE CLOSED BOOKING WHO GOT NOTIFIED 48H EARLIER **************");

    Optional<List<Booking>> bookingList = bookingDao.findBookingsByIsClosedIsTrue();
    List<Booking> bookingsNeedRemove = new ArrayList<>();

    bookingList.ifPresent(bookings -> {
      bookings.forEach(booking -> {
        DateTime closedBookingDate = new DateTime(booking.getDateOfClosing());
        DateTime deadLine = closedBookingDate.plusDays(2);
        if (DateTime.now().isAfter(deadLine)) {
          bookingsNeedRemove.add(booking);
        }
      });
    });
    if (!bookingsNeedRemove.isEmpty()) {
      bookingsNeedRemove.forEach(bookingDao::delete);
    }
  }

  protected static BiPredicate<Integer, Integer> lessThenTheDouble = (theBookingList, bookTotalStock) ->
      theBookingList < bookTotalStock * 2;
}






