package com.publicservice.librarywebapp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryRoleDto {

  protected Long id;
  protected String libraryUserRole;
}
