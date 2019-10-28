package com.publicservice.v1.controller;

import com.publicservice.business.contract.BookBusiness;
import com.publicservice.business.exception.BookNotFoundException;
import com.publicservice.entities.Book;
import com.publicservice.v1.dto.mapper.BookMapper;
import com.publicservice.v1.dto.model.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Books")
public class BookController {

  private final BookBusiness bookBusiness;
  private final BookMapper bookMapper;

  public BookController(BookBusiness bookBusiness,
      BookMapper bookMapper) {
    this.bookBusiness = bookBusiness;
    this.bookMapper = bookMapper;
  }

  @GetMapping(value = "/{id}")
  public BookDto findOneBookById(@PathVariable Long id) throws BookNotFoundException {
    Book book = bookBusiness.findOneBookById(id);
    return bookMapper.toBookDto(book);
  }

  @GetMapping(value = "/serche/{numPage}/{size}/{keyword}/{kindOfSerche}")
  public Page<Book> lookingForABook(@PathVariable int numPage, @PathVariable int size,
      @PathVariable String keyword, @PathVariable String kindOfSearch) {
    /** TODO */
    return null;
  }

}
