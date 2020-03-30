package com.publicservice.v1.controller;

import com.publicservice.v1.contract.BorrowBusiness;
import com.publicservice.entities.Borrow;
import com.publicservice.v1.dto.model.DelayBorrowUser;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delay")
public class BatchProviderController {


  private BorrowBusiness borrowBusiness;

  public BatchProviderController(BorrowBusiness borrowBusiness) {
    this.borrowBusiness = borrowBusiness;
  }

  @GetMapping("/all")
  public List<DelayBorrowUser> overTimeLimite() {
    List<Borrow>delayBorrows=borrowBusiness.borrowsOverTimeLimite();
    return delayBorrows.stream()
        .map(borrow -> getOneDelay(borrow))
        .collect(Collectors.toList());
  }

  public DelayBorrowUser getOneDelay(Borrow borrow){

    DelayBorrowUser delayBorrowUser = new DelayBorrowUser();

    delayBorrowUser.setFirstname(borrow.getUserID().getFirstname());
    delayBorrowUser.setEmail(borrow.getUserID().getEmail());
    delayBorrowUser.setDateEnd(borrow.getDateEnd());
    delayBorrowUser.setName(borrow.getBookID().getName());

    return delayBorrowUser;
  }
}
