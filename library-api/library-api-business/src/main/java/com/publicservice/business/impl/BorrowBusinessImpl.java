package com.publicservice.business.impl;

import com.publicservice.business.contract.BorrowBusiness;
import com.publicservice.consumer.BorrowDao;
import com.publicservice.entities.Borrow;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BorrowBusinessImpl implements BorrowBusiness {

  private BorrowDao borrowDao;



  @Override
  public Borrow findBorrowById(Long id) {
    return null;
  }

  @Override
  public void addExtraTime(Long id) {

  }

  @Override
  public Borrow createBorrow(String username) {
    return null;
  }

  @Override
  public void deleteBorrow(Long id) {

  }
}
