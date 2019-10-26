package com.publicservice.librarywebapp.bean;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBean {

  protected Long id;
  protected String name;
  protected String author;
  protected String subject;
  protected String overview;
  protected String publisher;
  protected Date publicationDate;
  protected String language;
  protected String coverPicUrl;
  protected Long library;
  protected List<BorrowBean> borrows;
  protected Long stock;

}
