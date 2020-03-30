package com.publicservice.v1.impl;

import com.publicservice.v1.contract.BookBusiness;
import com.publicservice.v1.exception.BookNotFoundException;
import com.publicservice.consumer.BookDao;
import com.publicservice.consumer.StockDao;
import com.publicservice.entities.Book;
import com.publicservice.entities.Stock;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class BookBusinessImpl implements BookBusiness {


  private final BookDao bookDao;
  private final StockDao stockDao;

  public BookBusinessImpl(BookDao bookDao, StockDao stockDao) {
    this.bookDao = bookDao;
    this.stockDao = stockDao;
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

  @Override
  public Stock findStockByBookId(Long id) throws BookNotFoundException {
    Book book = findOneBookById(id);
    return book.getStock();
  }
}
