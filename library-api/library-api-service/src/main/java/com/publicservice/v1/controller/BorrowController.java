package com.publicservice.v1.controller;

import com.publicservice.business.contract.BorrowBusiness;
import com.publicservice.business.exception.BorrowNotFoundException;
import com.publicservice.business.exception.ExtraTimeNotAllowed;
import com.publicservice.entities.Borrow;
import java.text.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowController {

  private BorrowBusiness borrowBusiness;

  public BorrowController(BorrowBusiness borrowBusiness) {
    this.borrowBusiness = borrowBusiness;
  }

  @GetMapping(value = "Borrows/{id}")
  public Borrow findBorrowById(@PathVariable Long id) throws BorrowNotFoundException {
    return borrowBusiness.findBorrowById(id);
  }

  @PostMapping(value = "Borrows/{id}/addExtraTime")
  public void addExtraTime(@PathVariable Long id)
      throws ParseException, BorrowNotFoundException, ExtraTimeNotAllowed {
    borrowBusiness.addExtraTime(id);
  }

  @PostMapping(value = "Borrows/newBorrow")
  public Borrow createBorrow(Borrow newBorrow){
    return borrowBusiness.createBorrow(newBorrow);
  }

}
