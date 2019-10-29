package com.publicservice.v1.dto.model;

import com.publicservice.entities.Borrow;
import java.util.Date;
import lombok.Data;


@Data
public class BookDto {

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
  protected Borrow borrow;
  protected Long stock;
}
