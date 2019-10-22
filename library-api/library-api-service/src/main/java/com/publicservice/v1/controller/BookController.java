package com.publicservice.v1.controller;

import com.publicservice.business.contract.BookBusiness;
import com.publicservice.business.exception.BookNotFoundException;
import com.publicservice.entities.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

  private BookBusiness bookBusiness;

  public BookController(BookBusiness bookBusiness) {
    this.bookBusiness = bookBusiness;
  }

  @GetMapping(value = "Books/{id}")
  public Book findOneBookById(@PathVariable Long id) throws BookNotFoundException {
    Book book = bookBusiness.findOneBookById(id);
    return book;
  }

}
