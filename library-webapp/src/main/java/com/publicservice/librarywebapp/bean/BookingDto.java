package com.publicservice.librarywebapp.bean;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

  protected BookingKeyDto id;
  protected Date dateCreation;
  protected Boolean isClosed;
  protected Boolean isNotified;
  protected Date dateOfClosing;
  protected int position;
  protected Date dateOfBookWillBeBack;
  protected String bookName;
}
