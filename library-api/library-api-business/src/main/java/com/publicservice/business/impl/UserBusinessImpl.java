package com.publicservice.business.impl;

import com.publicservice.business.contract.UserBusiness;
import com.publicservice.business.exception.LibraryUserNotFoundException;
import com.publicservice.consumer.UserDao;
import com.publicservice.entities.Borrow;
import com.publicservice.entities.LibraryUser;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserBusinessImpl implements UserBusiness {

  private final UserDao userDao;

  public UserBusinessImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public List<Borrow> checkeLibraryUserBorrowedBook(String username)
      throws LibraryUserNotFoundException {
    LibraryUser targetLibraryUser = oneLibraryUser(username);
    return targetLibraryUser.getBorrows();
  }

  @Override
  public LibraryUser oneLibraryUser(String username) throws LibraryUserNotFoundException {
    LibraryUser libraryUser = userDao.getOne(username);
    if (libraryUser == null) {
      throw new LibraryUserNotFoundException("can't find User");
    } else {
      return libraryUser;
    }
  }
}
