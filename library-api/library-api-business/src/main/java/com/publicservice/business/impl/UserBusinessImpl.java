package com.publicservice.business.impl;

import com.publicservice.business.contract.UserBusiness;
import com.publicservice.business.exception.LibraryUserNotFoundException;
import com.publicservice.consumer.UserDao;
import com.publicservice.entities.Borrow;
import com.publicservice.entities.LibraryUser;
import java.util.List;
import java.util.Optional;
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
    Optional<LibraryUser> targetLibraryUser = oneLibraryUser(username);
    return targetLibraryUser.get().getBorrows();
  }

  @Override
  public Optional<LibraryUser> oneLibraryUser(String username)
      throws LibraryUserNotFoundException {
    Optional<LibraryUser> libraryUser = userDao.findById(username);
    if (!libraryUser.isPresent()) {
      throw new LibraryUserNotFoundException("Can't find this " + username + " Library user !!");
    } else {
      return libraryUser;
    }
  }

  @Override
  public LibraryUser addLibraryUser(LibraryUser libraryUser) {
    userDao.save(libraryUser);
    return libraryUser;
  }
}
