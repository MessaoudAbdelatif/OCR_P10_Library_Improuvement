package com.publicservice.v1.controller;

import com.publicservice.business.contract.BookingBusiness;
import com.publicservice.business.contract.UserBusiness;
import com.publicservice.business.exception.LibraryUserNotFoundException;
import com.publicservice.entities.Booking;
import com.publicservice.entities.LibraryUser;
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


  public BookingController(BookingBusiness bookingBusiness,
      BookingMapper bookingMapper, UserBusiness userBusiness) {
    this.bookingBusiness = bookingBusiness;
    this.bookingMapper = bookingMapper;
    this.userBusiness = userBusiness;
  }

  @GetMapping(value = "/{username}")
  public List<BookingDto> findOneBookingById(@PathVariable String username)
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
  public Booking createBooking(BookingDto bookingDto,@PathVariable String bookID,@PathVariable String username) throws Exception {
    BookingKeyDto bookingKeyDto = new BookingKeyDto();
    bookingKeyDto.setBookID(bookID);
    bookingKeyDto.setLibraryUserID(username);
    bookingDto.setId(bookingKeyDto);
    Booking booking = bookingMapper.toBooking(bookingDto);
    return bookingBusiness.createBooking(booking);
  }
}
