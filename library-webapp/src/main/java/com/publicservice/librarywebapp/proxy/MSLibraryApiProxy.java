package com.publicservice.librarywebapp.proxy;

import com.publicservice.librarywebapp.bean.BookDto;
import com.publicservice.librarywebapp.bean.BookPageDto;
import com.publicservice.librarywebapp.bean.BookingDto;
import com.publicservice.librarywebapp.bean.BorrowDto;
import com.publicservice.librarywebapp.bean.LibarayUserBorrowInfoDto;
import com.publicservice.librarywebapp.bean.LibraryUserDto;
import com.publicservice.librarywebapp.bean.StockDto;
import java.util.List;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "MicroService-LibraryApiService")
@RibbonClient(name = "MicroService-LibraryApiService")
public interface MSLibraryApiProxy {

  @GetMapping(value = "borrows/{id}")
  BorrowDto findBorrowById(@PathVariable("id") Long id);

  @PostMapping(value = "borrows/{id}/addExtraTime")
  void addExtraTime(@PathVariable("id") Long id);

  @PostMapping(value = "admin/newBorrow")
  BorrowDto createBorrow(BorrowDto newBorrowDto);

  @GetMapping(value = "admin/{id}/close")
  void closeBorrow(@PathVariable("id") Long id);

  @GetMapping(value = "books/{id}")
  BookDto findOneBookById(@PathVariable("id") Long id);

  @GetMapping(value = "books/{id}/stocks")
  StockDto findStockByBookId(@PathVariable("id") Long id);

  @GetMapping(value = "users/{username}/borrows")
  List<BorrowDto> checkeLibraryUserBorrowedBook(@PathVariable("username") String username);

  @GetMapping(value = "users/{username}")
  LibraryUserDto findOneLibraryUser(@PathVariable("username") String username);

  @GetMapping(value = "books/search")
  BookPageDto lookingForABook(@RequestParam(value = "page", defaultValue = "0") int numPage,
      @RequestParam(value = "size", defaultValue = "5") int size,
      @RequestParam(value = "keyword", defaultValue = "") String keyword,
      @RequestParam(value = "kindOfSearch", defaultValue = "NAME") String kindOfSearch);

  @PostMapping(value = "users/newLibraryUser")
  LibraryUserDto createNewUser(@RequestBody LibraryUserDto libraryUserDto);

  @GetMapping("borrows/{username}/info")
  List<LibarayUserBorrowInfoDto> libraryUserBorrowsInfo(@PathVariable String username);

  @PostMapping(value = "/booking/{username}/{bookID}/add")
  BookingDto createBooking(BookingDto bookingDto,@PathVariable String bookID,@PathVariable String username);

  @GetMapping(value = "/booking/{username}")
  List<BookingDto> findBookingListByUserId(@PathVariable String username);
}