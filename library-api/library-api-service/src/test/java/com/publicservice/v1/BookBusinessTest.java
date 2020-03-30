package com.publicservice.v1;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.publicservice.consumer.BookDao;
import com.publicservice.entities.Book;
import com.publicservice.entities.Stock;
import com.publicservice.v1.exception.BookNotFoundException;
import com.publicservice.v1.impl.BookBusinessImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;


@ExtendWith(MockitoExtension.class)
public class BookBusinessTest {

  @InjectMocks
  private BookBusinessImpl classUnderTest;

  @Mock
  private BookDao bookDao;


  @Test
  public void findOneBookById_thenTriggerBookNotFoundException() {
    assertThrows(BookNotFoundException.class,
        () -> classUnderTest.findOneBookById(2L));
  }

  @Test
  public void findOneBookById_thenSuccess() throws BookNotFoundException {
    Book book = new Book();
    when(bookDao.getOne(1L)).thenReturn(book);

    Book foundBook = classUnderTest.findOneBookById(1L);

    assertThat(foundBook).isNotNull();
    assertThat(foundBook).isEqualTo(book);
    verify(bookDao).getOne(1L);
  }

  @Test
  public void lookingForABook_thenSuccess() {
    classUnderTest.lookingForABook(1, 1, "test", "PUBLISHER");

    verify(bookDao).findByPublisherContainsIgnoreCase("test", PageRequest.of(1, 1));
    verify(bookDao, never())
        .findByAuthorContainsIgnoreCase("test", PageRequest.of(1, 1));

  }

  @Test
  public void findStockByBookId_thenSuccess() throws BookNotFoundException {
    Book book = new Book();
    book.setId(1L);
    Stock stock = new Stock();
    book.setStock(stock);
    when(bookDao.getOne(1L)).thenReturn(book);

    Stock foundStock = classUnderTest.findStockByBookId(1L);

    assertThat(foundStock).isEqualTo(stock);
    verify(bookDao).getOne(1L);
  }

}
