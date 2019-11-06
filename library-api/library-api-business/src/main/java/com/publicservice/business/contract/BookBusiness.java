package com.publicservice.business.contract;

import com.publicservice.business.exception.BookNotFoundException;
import com.publicservice.entities.Book;
import com.publicservice.entities.Stock;
import org.springframework.data.domain.Page;

/**
 * use case business fonction findOneBookById. use case business fonction lookingForABook.
 */

public interface BookBusiness {

  /**
   * Used to get a book using his id.
   *
   * @param id is a specific book id.
   * @return a Book entity.
   * @throws BookNotFoundException we handle the risk if the result of search is null.
   */
  Book findOneBookById(Long id) throws BookNotFoundException;

  /**
   * Used to get list of book after request using keyword.
   *
   * @param keyword is syntax that user insert as keyword to find book.
   * @param kindOfSearch is book attribute that user want search using it.
   * @param numPage is the number of pages returned by the request result.
   * @param size is the number of books displayed in one page.
   * @return a List of Books dispatched in pages.
   */
  Page<Book> lookingForABook(int numPage, int size, String keyword, String kindOfSearch);

  /**
   * Used to get a stock of specific book by his id.
   *
   * @param id is book id.
   * @return a Stock entity.
   * @throws BookNotFoundException we handle the risk if the result of search is null.
   */
  Stock findStockByBookId(Long id) throws BookNotFoundException;
}
