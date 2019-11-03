package com.publicservice.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(max = 50)
  private String name;

  @NotNull
  @Size(max = 40)
  private String author;

  private String subject;
  private String overview;

  @NotNull
  private String publisher;

  @NotNull
  private Date publicationDate;

  private String language;
  private String coverPicUrl;

  @ManyToOne
  @JoinColumn(name = "library_id")
  private Library library;

  @OneToOne(cascade = CascadeType.ALL)
  private Borrow borrow;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Stock stockId;
}