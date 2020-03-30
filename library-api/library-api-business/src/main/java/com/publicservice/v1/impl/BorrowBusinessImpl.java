package com.publicservice.v1.impl;

import com.publicservice.v1.contract.BookBusiness;
import com.publicservice.v1.contract.BorrowBusiness;
import com.publicservice.v1.exception.BookNotFoundException;
import com.publicservice.v1.exception.BorrowNotFoundException;
import com.publicservice.v1.exception.ExtraTimeNotAllowed;
import com.publicservice.consumer.BookDao;
import com.publicservice.consumer.BookingDao;
import com.publicservice.consumer.BorrowDao;
import com.publicservice.entities.Book;
import com.publicservice.entities.BookingKey;
import com.publicservice.entities.Borrow;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class BorrowBusinessImpl implements BorrowBusiness {

  private final BorrowDao borrowDao;
  private final BookDao bookDao;
  private final BookingDao bookingDao;
  private final BookBusiness bookBusiness;


  public BorrowBusinessImpl(BorrowDao borrowDao, BookDao bookDao,
      BookingDao bookingDao, BookBusiness bookBusiness) {
    this.borrowDao = borrowDao;
    this.bookBusiness = bookBusiness;
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
    if (bookingDao.findByIdBookIDAndIdLibraryUserIDAndIsClosedFalse(newBorrow.getBookID().getId(),
        newBorrow.getUserID().getUsername()).isPresent()) {
      bookingDao.deleteById(
          new BookingKey(newBorrow.getUserID().getUsername(), newBorrow.getBookID().getId()));
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
    bookDao.save(book);
  }

  public Date nearestReturnDate(Long bookId) throws BookNotFoundException {
    Book oneBookById = bookBusiness.findOneBookById(bookId);
    Optional<List<Borrow>> rowList = borrowDao
        .findByBookIDOrderByDateEndAsc(oneBookById);
    Borrow borrow = rowList.get().get(0);
    return borrow.getDateEnd();
  }


}
