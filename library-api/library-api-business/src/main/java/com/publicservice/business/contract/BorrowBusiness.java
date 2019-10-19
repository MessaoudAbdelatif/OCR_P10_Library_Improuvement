package com.publicservice.business.contract;

import com.publicservice.entities.Borrow;

/**
 * use case business fonction findBorrowById. use case business fonction addExtraTime.
 */
public interface BorrowBusiness {

  /**
   * Used to find Borrow entity by his id.
   *
   * @param id is the borrowing id.
   * @return Borrow entity concerned.
   */
  Borrow findBorrowById(Long id);

  /**
   * Used to add extra time for borrowing period.
   *
   * @param id is the borrowing id.
   */
  void addExtraTime(Long id);

}
