package com.publicservice.v1.dto.model;

import java.util.Date;
import lombok.Data;

@Data
public class LibraryUserDto {

  protected String username;
  protected String lastname;
  protected String firstname;
  protected String password;
  protected String address;
  protected String zipCode;
  protected String email;
  protected Date creationDate;
  protected Boolean active;
  //protected List<String> borrows;
}
