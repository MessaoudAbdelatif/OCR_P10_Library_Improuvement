package com.publicservice.v1.controller;

import com.publicservice.business.contract.BookBusiness;
import com.publicservice.business.contract.BookingBusiness;
import com.publicservice.business.contract.UserBusiness;
import com.publicservice.business.exception.BookNotFoundException;
import com.publicservice.business.exception.LibraryUserNotFoundException;
import com.publicservice.entities.Book;
import com.publicservice.entities.Booking;
import com.publicservice.entities.LibraryUser;
import com.publicservice.v1.dto.mapper.BookMapper;
import com.publicservice.v1.dto.mapper.BookingKeyMapper;
import com.publicservice.v1.dto.mapper.BookingMapper;
import com.publicservice.v1.dto.model.BookingDto;
import com.publicservice.v1.dto.model.BookingKeyDto;
import java.util.List;
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
  BookMapper bookMapper;
  BookBusiness bookBusiness;


  public BookingController(BookingBusiness bookingBusiness,
      BookingMapper bookingMapper, UserBusiness userBusiness, BookMapper bookMapper,
      BookingKeyMapper bookingKeyMapper, BookBusiness bookBusiness) {
    this.bookingBusiness = bookingBusiness;
    this.bookingMapper = bookingMapper;
    this.bookingKeyMapper = bookingKeyMapper;
    this.userBusiness = userBusiness;
    this.bookMapper = bookMapper;
    this.bookBusiness = bookBusiness;
  }

  @GetMapping(value = "/{username}")
  public List<BookingDto> findOBookingListByUserId(@PathVariable String username)
      throws LibraryUserNotFoundException {
    LibraryUser libraryUser = userBusiness.oneLibraryUser(username);
    List<Booking> bookingList = bookingBusiness.getBookingByUserID(libraryUser);
    return bookingList
        .stream()
        .map(bookingMapper::toBookingDto)
        .collect(Collectors.toList());
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


}
