package com.publicservice.business.impl;

import com.publicservice.business.contract.UserBusiness;
import com.publicservice.consumer.UserDao;
import com.publicservice.entities.Borrow;
import com.publicservice.entities.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserBusinessImpl implements UserBusiness {

  private UserDao userDao;

  public UserBusinessImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public List<Borrow> checkeUserBorrowedBook(String username) {
    User targetUser = userDao.getOne(username);
    return targetUser.getBorrows();
  }
}
