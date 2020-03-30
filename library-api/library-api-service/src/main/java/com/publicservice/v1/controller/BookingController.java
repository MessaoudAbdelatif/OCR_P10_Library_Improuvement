package com.publicservice.v1.controller;

import com.publicservice.v1.contract.BookBusiness;
import com.publicservice.v1.contract.BookingBusiness;
import com.publicservice.v1.contract.BorrowBusiness;
import com.publicservice.v1.contract.UserBusiness;
import com.publicservice.v1.exception.BookNotFoundException;
import com.publicservice.v1.exception.BookingNotAllowed;
import com.publicservice.v1.exception.LibraryUserNotFoundException;
import com.publicservice.entities.Book;
import com.publicservice.entities.Booking;
import com.publicservice.entities.BookingKey;
import com.publicservice.entities.LibraryUser;
import com.publicservice.v1.dto.mapper.BookingKeyMapper;
import com.publicservice.v1.dto.mapper.BookingMapper;
import com.publicservice.v1.dto.model.BookingDto;
import com.publicservice.v1.dto.model.BookingKeyDto;
import com.publicservice.v1.dto.model.DelayBorrowUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

  BookingBusiness bookingBusiness;
  UserBusiness userBusiness;
  BookingMapper bookingMapper;
  BookingKeyMapper bookingKeyMapper;
  BookBusiness bookBusiness;
  BorrowBusiness borrowBusiness;


  public BookingController(BookingBusiness bookingBusiness,
      UserBusiness userBusiness, BookBusiness bookBusiness, BorrowBusiness borrowBusiness,
      BookingMapper bookingMapper,
      BookingKeyMapper bookingKeyMapper) {
    this.bookingBusiness = bookingBusiness;
    this.userBusiness = userBusiness;
    this.bookBusiness = bookBusiness;
    this.borrowBusiness = borrowBusiness;
    this.bookingMapper = bookingMapper;
    this.bookingKeyMapper = bookingKeyMapper;
  }

  @GetMapping(value = "/{username}")
  public List<BookingDto> findBookingListByUserId(@PathVariable String username)
      throws LibraryUserNotFoundException {
    LibraryUser libraryUser = userBusiness.oneLibraryUser(username);
    List<Booking> bookingList = bookingBusiness.getBookingByUserID(libraryUser);
    List<BookingDto> bookingDtoList = bookingList.stream().map(bookingMapper::toBookingDto).collect(
        Collectors.toList());
    bookingDtoList.forEach(bookingDto -> {
      try {
        bookingDto.setPosition(
            bookingBusiness.myPositionInQueue(bookingKeyMapper.toBookingKey(bookingDto.getId())));
      } catch (BookingNotAllowed bookingNotAllowed) {
        bookingNotAllowed.printStackTrace();
      }
    });

    bookingDtoList.forEach(bookingDto -> {
      try {
        bookingDto.setDateOfBookWillBeBack(
            borrowBusiness.nearestReturnDate(Long.parseLong(bookingDto.getId().getBookID())));
      } catch (BookNotFoundException e) {
        e.printStackTrace();
      }
    });

    bookingDtoList.forEach(bookingDto -> {
      try {
        bookingDto.setBookName(
            bookBusiness.findOneBookById(Long.parseLong(bookingDto.getId().getBookID())).getName());
      } catch (BookNotFoundException e) {
        e.printStackTrace();
      }
    });

    return bookingDtoList;
  }

  @PostMapping(value = "/{username}/{bookID}/add")
  @ResponseStatus(HttpStatus.CREATED)
  public BookingDto createBooking(@PathVariable String bookID,
      @PathVariable String username) throws Exception {
    BookingKeyDto bookingKeyDto = new BookingKeyDto();
    BookingDto bookingDto = new BookingDto();

    bookingKeyDto.setBookID(bookID);
    bookingKeyDto.setLibraryUserID(username);
    bookingDto.setId(bookingKeyDto);
    Booking booking = bookingMapper.toBooking(bookingDto);
    bookingBusiness.createBooking(booking);

    Booking bookingByIdFromDao = bookingBusiness
        .getBookingById(bookingKeyMapper.toBookingKey(bookingKeyDto));

    return bookingMapper.toBookingDto(bookingByIdFromDao);
  }

  @GetMapping(value = "/book/{bookId}")
  public boolean bookingListIsNotFull(@PathVariable String bookId) throws BookNotFoundException {
    Book oneBookById = bookBusiness.findOneBookById(Long.parseLong(bookId));
    return bookingBusiness.bookingListIsNotFull(oneBookById);
  }

  @GetMapping(value = "/{username}/{bookID}/position")
  public int myPositionInTheQueue(@PathVariable String username, @PathVariable String bookID)
      throws BookingNotAllowed {
    BookingKey bookingKey = new BookingKey();
    bookingKey.setBookID(Long.parseLong(bookID));
    bookingKey.setLibraryUserID(username);
    return bookingBusiness.myPositionInQueue(bookingKey);
  }

  @PostMapping(value = "/{bookId}/{username}")
  public void deleteBooking(@PathVariable("bookId") String bookId,
      @PathVariable("username") String username) {
    BookingKey bookingKey = new BookingKey();
    bookingKey.setBookID(Long.parseLong(bookId));
    bookingKey.setLibraryUserID(username);
    bookingBusiness.deleteBookingById(bookingKey);
  }

  @GetMapping(value = "/size/{bookId}")
  public int bookingListSize(@PathVariable("bookId") String bookId) {
    return bookingBusiness.bookingListSize(Long.parseLong(bookId));
  }

  @GetMapping(value = "/{bookId}/{username}/canBook")
  public boolean canBookABook(@PathVariable("bookId") Long bookId,
      @PathVariable("username") String username)
      throws LibraryUserNotFoundException, BookNotFoundException {
    Book oneBookById = bookBusiness.findOneBookById(bookId);
    return bookingBusiness.canBookABook(oneBookById, username);
  }

  @GetMapping(value = "/notification")
  public List<DelayBorrowUser> notifyBookedUser() {
    Optional<List<Booking>> bookings = bookingBusiness.allBookingsClosedNotNotified();
    List<DelayBorrowUser> delayBorrowUsers = new ArrayList<>();
    bookings.ifPresent(bookingList -> bookingList.forEach(booking -> {
      DelayBorrowUser delayBorrowUser = new DelayBorrowUser();
      LibraryUser libraryUser = null;
      try {
        libraryUser = userBusiness.oneLibraryUser(booking.getId().getLibraryUserID());
      } catch (LibraryUserNotFoundException e) {
        e.printStackTrace();
      }
      delayBorrowUser.setFirstname(libraryUser.getFirstname());
      delayBorrowUser.setEmail(libraryUser.getEmail());
      Book oneBookById = null;
      try {
        oneBookById = bookBusiness.findOneBookById(booking.getId().getBookID());
      } catch (BookNotFoundException e) {
        e.printStackTrace();
      }
      delayBorrowUser.setName(oneBookById.getName());
      delayBorrowUsers.add(delayBorrowUser);
      booking.setIsNotified(true);
      bookingBusiness.updateBookingInfo(booking);
    }));
    return delayBorrowUsers;
  }
}
