package com.publicservice.business.impl;

import com.publicservice.business.contract.BorrowBusiness;
import com.publicservice.business.exception.BorrowNotFoundException;
import com.publicservice.business.exception.ExtraTimeNotAllowed;
import com.publicservice.consumer.BookDao;
import com.publicservice.consumer.BookingDao;
import com.publicservice.consumer.BorrowDao;
import com.publicservice.entities.Book;
import com.publicservice.entities.BookingKey;
import com.publicservice.entities.Borrow;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class BorrowBusinessImpl implements BorrowBusiness {

  private final BorrowDao borrowDao;
  private final BookDao bookDao;
  private final BookingDao bookingDao;


  public BorrowBusinessImpl(BorrowDao borrowDao, BookDao bookDao,
      BookingDao bookingDao) {
    this.borrowDao = borrowDao;

    this.bookDao = bookDao;
    this.bookingDao = bookingDao;
  }

  @Override
  public Borrow findBorrowById(Long id) throws BorrowNotFoundException {
    Borrow targetBorrow = borrowDao.getOne(id);
    if (targetBorrow == null) {
      throw new BorrowNotFoundException("Borrow not found !!");
    }
    return targetBorrow;
  }

  @Override
  public void addExtraTime(Long id, int extraTime)
      throws BorrowNotFoundException, ExtraTimeNotAllowed {
    Borrow borrow = findBorrowById(id);
    if (!borrow.getExtraTime()) {
      DateTime dt = new DateTime(borrow.getDateEnd());
      DateTime newdt = dt.plusDays(extraTime);
      Date newEndDate = newdt.toDate();
      borrow.setDateEnd(newEndDate);
      borrow.setExtraTime(true);
    } else {
      throw new ExtraTimeNotAllowed("You already get an extra time !");
    }

  }

  @Override
  public Borrow createBorrow(Borrow newBorrow, int initialTime) {
    if (bookingDao.findByIdBookIDAndIdLibraryUserIDAndIsClosedFalse(newBorrow.getBookID(),
        newBorrow.getUserID()).isPresent()) {
      bookingDao.deleteById(new BookingKey(newBorrow.getUserID(), newBorrow.getBookID()));
    }
    newBorrow.setDateStart(Date.from(Instant.now()));
    DateTime dateStart = new DateTime(newBorrow.getDateStart());
    DateTime dateEnd = dateStart.plusDays(initialTime);
    Date calculatedEndDate = dateEnd.toDate();
    newBorrow.setDateEnd(calculatedEndDate);
    newBorrow.setClosed(false);
    newBorrow.setExtraTime(false);

    borrowDao.save(newBorrow);

    return newBorrow;
  }

  @Override
  public void closeBorrow(Long id) {
    Borrow borrow = borrowDao.getOne(id);
    borrow.setClosed(true);
    borrowDao.save(borrow);
  }

  @Override
  public List<Borrow> borrowsOverTimeLimite() {
    List<Borrow> borrowsOverDeadTime = borrowDao
        .findBorrowByDateEndBeforeAndClosedFalse(Date.from(Instant.now()));
    return borrowsOverDeadTime;
  }

  public void returnedBook(Book book) {
    book.getStock().setOutside((book.getStock().getOutside()) - 1);
    book.getStock().setAvailable(book.getStock().getAvailable() + 1);
    // TODO notify booking queue that book is back

    bookDao.save(book);
  }
}
