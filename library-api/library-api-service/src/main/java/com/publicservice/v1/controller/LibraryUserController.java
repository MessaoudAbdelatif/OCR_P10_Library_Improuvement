package com.publicservice.v1.controller;


import com.publicservice.business.contract.UserBusiness;
import com.publicservice.business.exception.LibraryUserNotFoundException;
import com.publicservice.entities.LibraryUser;
import com.publicservice.v1.dto.mapper.BorrowMapper;
import com.publicservice.v1.dto.mapper.LibraryUserMapper;
import com.publicservice.v1.dto.model.BorrowDto;
import com.publicservice.v1.dto.model.LibraryUserDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryUserController {

  private final UserBusiness userBusiness;
  private final BorrowMapper borrowMapper;
  private final LibraryUserMapper libraryUserMapper;

  public LibraryUserController(UserBusiness userBusiness,
      BorrowMapper borrowMapper,
      LibraryUserMapper libraryUserMapper) {
    this.userBusiness = userBusiness;
    this.borrowMapper = borrowMapper;
    this.libraryUserMapper = libraryUserMapper;
  }

  @GetMapping(value = "Users/{username}/Borrows")
  public List<BorrowDto> checkeLibraryUserBorrowedBook(@PathVariable String username)
      throws LibraryUserNotFoundException {
    return userBusiness.checkeLibraryUserBorrowedBook(username)
        .stream()
        .map(borrowMapper::toBorrowDto)
        .collect(Collectors.toList());
  }

  @GetMapping(value = "Users/{username}")
  public LibraryUserDto findOneLibraryUser(@PathVariable String username)
      throws LibraryUserNotFoundException {
    LibraryUser libraryUser = userBusiness.oneLibraryUser(username);
    return libraryUserMapper.toLibraryUserDto(libraryUser);
  }

}
