package com.publicservice.librarywebapp.bean;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryUserDto {

  protected String username;
  protected String lastname;
  protected String firstname;
  protected String password;
  protected String passwordRetype;
  protected String address;
  protected String email;
  protected Date creationDate;
  protected Boolean active;
  protected List<BorrowDto> borrows;
  private List<LibraryRoleDto> roles;

}
