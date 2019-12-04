package com.publicservice.librarybatch.model;

import java.util.Date;
import lombok.Data;

@Data
public class DelayBorrowUser {

  protected String firstname;
  protected String name;
  protected String email;
  protected Date dateEnd;
}
