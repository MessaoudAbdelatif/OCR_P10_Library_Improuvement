package com.publicservice.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_reader")
public class LibraryUser implements Serializable {

  @Id
  private String username;

  private String lastname;
  private String firstname;
  private String password;
  private String address;
  @Email
  private String email;

  private Date creationDate;
  private Boolean active;

  @OneToMany(mappedBy = "userID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Borrow> borrows;
}
