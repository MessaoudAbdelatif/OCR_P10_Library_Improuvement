package com.publicservice.v1.controller;

import com.publicservice.business.contract.UserBusiness;
import com.publicservice.v1.dto.mapper.BorrowMapper;
import com.publicservice.v1.dto.model.BorrowDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryUserController {

  private final UserBusiness userBusiness;
  private final BorrowMapper borrowMapper;

  public LibraryUserController(UserBusiness userBusiness,
      BorrowMapper borrowMapper) {
    this.userBusiness = userBusiness;
    this.borrowMapper = borrowMapper;
  }

  @GetMapping(value = "Users/{username}")
  public List<BorrowDto> checkeLibraryUserBorrowedBook(@PathVariable String username) {
    return userBusiness.checkeLibraryUserBorrowedBook(username)
        .stream()
        .map(borrowMapper::toBorrowDto)
        .collect(Collectors.toList());
  }

}
