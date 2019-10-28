package com.publicservice.business.impl;

import com.publicservice.business.contract.BookBusiness;
import com.publicservice.business.exception.BookNotFoundException;
import com.publicservice.consumer.BookDao;
import com.publicservice.entities.Book;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class BookBusinessImpl implements BookBusiness {


  private final BookDao bookDao;

  public BookBusinessImpl(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Override
  public Book findOneBookById(Long id) throws BookNotFoundException {
    Book targetBook = bookDao.getOne(id);
    if (targetBook == null) {
      throw new BookNotFoundException("Book not found !!");
    }
    return targetBook;
  }

  @Override
  public Page<Book> lookingForABook(int numPage, int size, String keyword, String kindOfSearch) {
    switch (kindOfSearch) {
      case "PUBLISHER":
        return bookDao.findByPublisherContainsIgnoreCase(keyword, PageRequest.of(numPage, size));
      case "AUTHOR":
        return bookDao.findByAuthorContainsIgnoreCase(keyword, PageRequest.of(numPage, size));
      default:
        return bookDao
            .findByNameContainsIgnoreCaseOrderByName(keyword, PageRequest.of(numPage, size));
    }
  }
}
