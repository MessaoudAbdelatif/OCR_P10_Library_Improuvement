package com.publicservice.librarywebapp.bean;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDto {
  protected Long id;
  protected String username;
  protected Long bookId;
  protected Date dateStart;
  protected Date dateEnd;
  protected Boolean extraTime;

}
