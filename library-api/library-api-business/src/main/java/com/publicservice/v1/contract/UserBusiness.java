package com.publicservice.v1.contract;

import com.publicservice.v1.exception.LibraryUserNotFoundException;
import com.publicservice.entities.Borrow;
import com.publicservice.entities.LibraryUser;
import java.util.List;

/**
 * use case business fonction checkeLibraryReaderBorrowedBook.
 */
public interface UserBusiness {

  /**
   * Used to get a list of borrowing books for specific user.
   *
   * @param username is user id.
   * @return List of borrows entities.
   */
  List<Borrow> checkeLibraryUserBorrowedBook(String username) throws LibraryUserNotFoundException;

  /**
   * Used to get a LibraryUser entity by Id
   *
   * @param username is the libraryUser id.
   * @return One LibraryUser entity.
   * @throws LibraryUserNotFoundException handle if there is not user for this id.
   */
  LibraryUser oneLibraryUser(String username) throws LibraryUserNotFoundException;

  /**
   * Used to register a new user
   *
   * @param libraryUser is the json transit by LibraryUserDto posted.
   * @return One LibraryUser entity.
   */
  void saveOneLibraryUser(LibraryUser libraryUser);

//  void addRoleToLibraryUser(String username, String rolename);


}

