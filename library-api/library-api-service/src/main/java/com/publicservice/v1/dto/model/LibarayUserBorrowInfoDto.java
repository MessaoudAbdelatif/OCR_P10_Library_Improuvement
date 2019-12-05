package com.publicservice.v1.dto.model;

import java.util.Date;
import lombok.Data;

@Data
public class LibarayUserBorrowInfoDto {

  protected String lastname;
  protected String firstname;
  protected String bookName;
  protected Date endDate;
  protected boolean extraTime;
  protected Long borrowID;
  protected String borrowIDS;

}
