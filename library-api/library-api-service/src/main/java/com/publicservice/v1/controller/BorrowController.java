package com.publicservice.v1.controller;

import com.publicservice.v1.contract.BorrowBusiness;
import com.publicservice.v1.contract.UserBusiness;
import com.publicservice.v1.exception.BorrowNotFoundException;
import com.publicservice.v1.exception.ExtraTimeNotAllowed;
import com.publicservice.v1.exception.LibraryUserNotFoundException;
import com.publicservice.entities.Borrow;
import com.publicservice.v1.configuration.ApplicationPropertiesConfiguration;
import com.publicservice.v1.dto.mapper.BorrowMapper;
import com.publicservice.v1.dto.model.BorrowDto;
import com.publicservice.v1.dto.model.LibarayUserBorrowInfoDto;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("borrows")
public class BorrowController {

  private final BorrowBusiness borrowBusiness;
  private final BorrowMapper borrowMapper;
  private final UserBusiness userBusiness;
  private final ApplicationPropertiesConfiguration appProperties;


  public BorrowController(BorrowBusiness borrowBusiness,
      BorrowMapper borrowMapper,
      UserBusiness userBusiness,
      ApplicationPropertiesConfiguration appProperties) {
    this.borrowBusiness = borrowBusiness;
    this.borrowMapper = borrowMapper;
    this.userBusiness = userBusiness;
    this.appProperties = appProperties;
  }

  @GetMapping(value = "/{id}")
  public BorrowDto findBorrowById(@PathVariable Long id) throws BorrowNotFoundException {
    return borrowMapper.toBorrowDto(borrowBusiness.findBorrowById(id));
  }

  @PostMapping(value = "/{id}/addExtraTime")
  @ResponseStatus(HttpStatus.OK)
  public void addExtraTime(@PathVariable Long id)
      throws ParseException, BorrowNotFoundException, ExtraTimeNotAllowed {
    borrowBusiness.addExtraTime(id, appProperties.getExtraTime());
  }

  @PostMapping(value = "admin/newBorrow")
  @ResponseStatus(HttpStatus.CREATED)
  public Borrow createBorrow(BorrowDto newBorrowDto) {
    Borrow borrow = borrowMapper.toBorrow(newBorrowDto);
    return borrowBusiness.createBorrow(borrow, appProperties.getInitialTime());
  }

  @GetMapping(value = "admin/{id}/close")
  @ResponseStatus(HttpStatus.OK)
  public void closeBorrow(@PathVariable("id") Long id){
    borrowBusiness.closeBorrow(id);
  }

  @GetMapping(value = "/delay")
  public List<BorrowDto> borrowsOverTimeLimite() {

    return borrowBusiness.borrowsOverTimeLimite()
        .stream()
        .map(borrowMapper::toBorrowDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/{username}/info")
  public List<LibarayUserBorrowInfoDto> libraryUserBorrowsInfo(@PathVariable String username)
      throws LibraryUserNotFoundException {

    List<Borrow> borrows = userBusiness.oneLibraryUser(username).getBorrows();
    if (borrows.isEmpty()) {
      return Collections.emptyList();
    }
    return borrows.stream()
        .map(this::getUserInfo)
        .collect(Collectors.toList());
  }

  public LibarayUserBorrowInfoDto getUserInfo(Borrow borrow) {
    LibarayUserBorrowInfoDto lUBID = new LibarayUserBorrowInfoDto();

    lUBID.setFirstname(borrow.getUserID().getFirstname());
    lUBID.setLastname(borrow.getUserID().getLastname());
    lUBID.setBookName(borrow.getBookID().getName());
    lUBID.setEndDate(borrow.getDateEnd());
    lUBID.setExtraTime(borrow.getExtraTime());
    lUBID.setBorrowID(borrow.getId());
    lUBID.setBorrowIDS(borrow.getId().toString());

    return lUBID;
  }


}
