package com.publicservice.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  @Id
  @NotEmpty
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
