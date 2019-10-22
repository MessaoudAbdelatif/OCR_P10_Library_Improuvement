package com.publicservice.v1.controller;

import com.publicservice.business.contract.UserBusiness;
import com.publicservice.entities.Borrow;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryUserController {

  private UserBusiness userBusiness;

  public LibraryUserController(UserBusiness userBusiness) {
    this.userBusiness = userBusiness;
  }

  @GetMapping(value = "Users/{username}")
  public List<Borrow> checkeLibraryUserBorrowedBook(@PathVariable String username) {
    return userBusiness.checkeLibraryUserBorrowedBook(username);
  }

}
