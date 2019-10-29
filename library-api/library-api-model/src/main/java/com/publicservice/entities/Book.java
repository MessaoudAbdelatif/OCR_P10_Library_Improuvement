package com.publicservice.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Book implements Serializable {

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

  @ManyToOne
  @JoinColumn(name = "library_id")
  private Library library;

  @OneToOne(cascade = CascadeType.ALL)
  private Borrow borrow;

  @OneToOne(cascade = CascadeType.ALL)
  private Stock stockId;
}
