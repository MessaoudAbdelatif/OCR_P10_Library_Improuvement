package com.publicservice.business.contract;

import com.publicservice.entities.Borrow;

/**
 * use case business fonction findBorrowById. use case business fonction addExtraTime.
 */
public interface BorrowBusiness {

  /**
   * @param id is the borrowing id.
   */
  Borrow findBorrowById(Long id);

  /**
   * @param id is the borrowing id.
   */
  void addExtraTime(Long id);

}
