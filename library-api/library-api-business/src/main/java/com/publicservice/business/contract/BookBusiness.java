package com.publicservice.business.contract;


import com.publicservice.entities.Book;
import org.springframework.data.domain.Page;

/**
 * use case business fonction findOneBookById. use case business fonction lookingForABook.
 */

public interface BookBusiness {

  /**
   * @param id is a specific book id
   */
  Book findOneBookById(Long id);

  /**
   * @param keyword is syntax that user insert as keyword to find book.
   * @param kindOfSearch is book attribute that user want search using it.
   * @param numPage is the number of pages returned by the request result.
   * @param size is the number of books displayed in one page.
   */
  Page<Book> lookingForABook(int numPage, int size, String keyword, String kindOfSearch);


}
