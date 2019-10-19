package com.publicservice.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty
  private String name;
  @NotEmpty
  private String author;
  private String subject;
  private String overview;
  @NotEmpty
  private String publisher;
  @NotEmpty
  private Date publicationDate;
  private String language;
  private String coverPicUrl;
  @OneToOne
  @JoinColumn(name = "library_id")
  private Library library;
  @OneToOne
  @JoinColumn(name = "borrow_id")
  private Borrow borrow;

}
