package com.publicservice.v1.dto.model;

import java.util.Date;
import lombok.Data;

@Data
public class BookingDto {

  protected BookingKeyDto id;
  protected Date dateCreation;
  protected Boolean isClosed;
  protected Date dateOfClosing;
  protected int position;
  protected Date dateOfBookWillBeBack;
  protected String bookName;
}
