package com.publicservice.business.impl;

import com.publicservice.business.contract.BookBusiness;
import com.publicservice.consumer.BookDao;
import com.publicservice.entities.Book;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class BookBusinessImpl implements BookBusiness {


  private BookDao bookDao;

  public BookBusinessImpl(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Override
  public Book findOneBookById(Long id) {
    return null;
  }

  @Override
  public Page<Book> lookingForABook(int numPage, int size, String keyword, String kindOfSearch) {
    return null;
  }
}
