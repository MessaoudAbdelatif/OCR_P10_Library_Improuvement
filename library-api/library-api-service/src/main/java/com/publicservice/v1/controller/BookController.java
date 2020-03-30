package com.publicservice.v1.controller;

import com.publicservice.v1.contract.BookBusiness;
import com.publicservice.v1.contract.BookingBusiness;
import com.publicservice.v1.contract.BorrowBusiness;
import com.publicservice.v1.contract.UserBusiness;
import com.publicservice.v1.exception.BookNotFoundException;
import com.publicservice.entities.Book;
import com.publicservice.entities.Booking;
import com.publicservice.v1.dto.mapper.BookMapper;
import com.publicservice.v1.dto.mapper.BookingKeyMapper;
import com.publicservice.v1.dto.mapper.StockMapper;
import com.publicservice.v1.dto.model.BookDto;
import com.publicservice.v1.dto.model.BookPageDto;
import com.publicservice.v1.dto.model.StockDto;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/books")
public class BookController {

  private final BookBusiness bookBusiness;
  private final BookMapper bookMapper;
  private final StockMapper stockMapper;
  private final BorrowBusiness borrowBusiness;
  private BookingBusiness bookingBusiness;
  private BookingKeyMapper bookingKeyMapper;
  private UserBusiness userBusiness;

  public BookController(BookBusiness bookBusiness,
      BookMapper bookMapper, StockMapper stockMapper,
      BorrowBusiness borrowBusiness,
      BookingBusiness bookingBusiness,
      BookingKeyMapper bookingKeyMapper,
      UserBusiness userBusiness) {
    this.bookBusiness = bookBusiness;
    this.bookMapper = bookMapper;
    this.stockMapper = stockMapper;
    this.borrowBusiness = borrowBusiness;
    this.bookingBusiness = bookingBusiness;
    this.bookingKeyMapper = bookingKeyMapper;
    this.userBusiness = userBusiness;
  }

  @GetMapping(value = "/{id}")
  public BookDto findOneBookById(@PathVariable Long id) throws BookNotFoundException {
    Book book = bookBusiness.findOneBookById(id);

    return bookMapper.toBookDto(book);
  }

  @GetMapping(value = "/{id}/stocks")
  public StockDto findStockByBookId(@PathVariable Long id) throws BookNotFoundException {
    return stockMapper.toStockDto(bookBusiness.findStockByBookId(id));
  }

  @GetMapping(value = "/search")
  public BookPageDto lookingForABook(@RequestParam(value = "page", defaultValue = "0") int numPage,
      @RequestParam(value = "size", defaultValue = "5") int size,
      @RequestParam(value = "keyword", defaultValue = "") String keyword,
      @RequestParam(value = "kindOfSearch", defaultValue = "NAME") String kindOfSearch) {
    Page<Book> booksUnderPage = bookBusiness.lookingForABook(numPage, size, keyword, kindOfSearch);
    List<Book> booksUnderList = booksUnderPage.getContent();
    BookPageDto bookPageDto = new BookPageDto();
    bookPageDto.setBooksDto(booksUnderList
        .stream()
        .map(bookMapper::toBookDto)
        .collect(Collectors.toList()));
    bookPageDto.setSize(size);
    bookPageDto.setKeyword(keyword);
    bookPageDto.setKindOfSearch(kindOfSearch);
    bookPageDto.setPage(numPage);
    bookPageDto.setTotalPages(new int[booksUnderPage.getTotalPages()]);
    bookPageDto.setNbrTotalPages(new int[booksUnderPage.getTotalPages()].length);

    return bookPageDto;
  }

  @GetMapping(value = "/{id}/back")
  public void bookIsBack(@PathVariable("id") String id)
      throws BookNotFoundException {
    Book oneBookById = bookBusiness.findOneBookById(Long.parseLong(id));
    borrowBusiness.returnedBook(oneBookById);
    if (bookingBusiness.bookingListSize(Long.parseLong(id)) > 0) {
      //Close Booking.
      Booking booking = bookingBusiness.theHeadOfList(Long.parseLong(id));
      booking.setIsClosed(true);
      booking.setDateOfClosing(Date.from(Instant.now()));
      bookingBusiness.updateBookingInfo(booking);
    }
  }
}
