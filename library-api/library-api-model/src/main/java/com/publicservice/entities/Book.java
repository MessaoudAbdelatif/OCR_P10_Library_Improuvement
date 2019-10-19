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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book  implements Serializable {

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
  @OneToMany(mappedBy = "bookID", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Borrow> borrows;

}
