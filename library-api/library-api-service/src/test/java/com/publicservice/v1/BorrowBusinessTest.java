package com.publicservice.v1;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.publicservice.consumer.BookDao;
import com.publicservice.consumer.BorrowDao;
import com.publicservice.entities.Book;
import com.publicservice.entities.Borrow;
import com.publicservice.entities.Stock;
import com.publicservice.v1.exception.BorrowNotFoundException;
import com.publicservice.v1.impl.BorrowBusinessImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BorrowBusinessTest {

  @InjectMocks
  BorrowBusinessImpl classUnderTest;
  @Mock
  BorrowDao borrowDao;
  @Mock
  BookDao bookDao;

  @Test
  public void findBorrowById_thenCallDao_andTriggerBorrowNotFoundException()
      throws BorrowNotFoundException {
    Borrow borrow = new Borrow();
    when(borrowDao.getOne(1L)).thenReturn(borrow);
    classUnderTest.findBorrowById(1L);

    verify(borrowDao).getOne(1L);

    assertThrows(BorrowNotFoundException.class,
        () -> classUnderTest.findBorrowById(2L));
  }

  @Test
  public void closeBorrow_thenCallDao() {
    Borrow borrow = new Borrow();
    when(borrowDao.getOne(1L)).thenReturn(borrow);

    classUnderTest.closeBorrow(1L);

    verify(borrowDao).getOne(1L);
  }

  @Test
  void returnedBook_thenCallDao() {
    //Given
    Book book = new Book();
    Stock stock = new Stock();
    stock.setOutside(1234);
    stock.setAvailable(1234);
    book.setStock(stock);
    //When
    classUnderTest.returnedBook(book);
    //Then
    verify(bookDao).save(book);
  }
}
