package com.publicservice.v1.impl;

import com.publicservice.v1.contract.UserBusiness;
import com.publicservice.v1.exception.LibraryUserNotFoundException;
import com.publicservice.consumer.UserDao;
import com.publicservice.entities.Borrow;
import com.publicservice.entities.LibraryUser;
import java.time.Instant;
import java.util.Date;
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
    LibraryUser targetLibraryUser = oneLibraryUser(username);
    return targetLibraryUser.getBorrows();
  }

  @Override
  public LibraryUser oneLibraryUser(String username)
      throws LibraryUserNotFoundException {
    Optional<LibraryUser> libraryUser = userDao.findById(username);
    if (!libraryUser.isPresent()) {
      throw new LibraryUserNotFoundException("Can't find this " + username + " Library user !!");
    } else {
      return libraryUser.get();
    }
  }

  @Override
  public void saveOneLibraryUser(LibraryUser libraryUser) {
//    if (userDao.findById(libraryUser.getUsername())!= null) {
//      throw new LibraryUserNotFoundException("User already exists");
//    }
    libraryUser.setCreationDate(Date.from(Instant.now()));
    libraryUser.setActive(true);
    String pwd = libraryUser.getPassword();

    userDao.save(libraryUser);
  }

}