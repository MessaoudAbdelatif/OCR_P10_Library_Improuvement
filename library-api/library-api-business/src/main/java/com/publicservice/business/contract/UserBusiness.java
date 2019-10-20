package com.publicservice.business.contract;

import com.publicservice.entities.Borrow;
import java.util.List;

/**
 * use case business fonction checkeUserBorrowedBook.
 */
public interface UserBusiness {

  /**
   * Used to get a list of borrowing books for specific user.
   *
   * @param username is user id
   * @return List of borrows entities.
   */
  List<Borrow> checkeUserBorrowedBook(String username);

}