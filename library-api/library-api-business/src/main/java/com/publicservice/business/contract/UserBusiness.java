package com.publicservice.business.contract;

import com.publicservice.entities.Borrow;
import java.util.List;

/**
 * use case business fonction checkeUserBorrowedBook.
 */
public interface UserBusiness {

  /**
   * @param username is user id
   */
  List<Borrow> checkeUserBorrowedBook(String username);

}
