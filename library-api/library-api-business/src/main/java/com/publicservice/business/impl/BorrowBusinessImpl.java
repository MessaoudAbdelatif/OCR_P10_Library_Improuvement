package com.publicservice.business.impl;

import com.publicservice.business.contract.BorrowBusiness;
import com.publicservice.business.exception.BorrowNotFoundException;
import com.publicservice.business.exception.ExtraTimeNotAllowed;
import com.publicservice.consumer.BorrowDao;
import com.publicservice.entities.Borrow;
import java.time.Instant;
import java.util.Date;
import javax.transaction.Transactional;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BorrowBusinessImpl implements BorrowBusiness {

  private final BorrowDao borrowDao;

  public BorrowBusinessImpl(BorrowDao borrowDao) {
    this.borrowDao = borrowDao;
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
  public void addExtraTime(Long id) throws BorrowNotFoundException, ExtraTimeNotAllowed {
    Borrow borrow = findBorrowById(id);
    if (!borrow.getExtraTime()) {
      DateTime dt = new DateTime(borrow.getDateEnd());
      //  private static ResourceBundle resourceBundle = ResourceBundle.getBundle(
      //      "WEB-INF/classes/application.properties");
      //  private static int initialTime = Integer.parseInt(resourceBundle.getString("InitialTime"));
      //  private static int extraTime = Integer.parseInt(resourceBundle.getString("ExtraTime"));
      int extraTime = 28;
      DateTime newdt = dt.plusDays(extraTime);
      Date newEndDate = newdt.toDate();
      borrow.setDateEnd(newEndDate);
      borrow.setExtraTime(true);
    } else {
      throw new ExtraTimeNotAllowed("You already get an extra time !");
    }

  }

  @Override
  public Borrow createBorrow(Borrow newBorrow) {
    newBorrow.setDateStart(Date.from(Instant.now()));
    DateTime dateStart = new DateTime(newBorrow.getDateStart());
    int initialTime = 28;
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
}
