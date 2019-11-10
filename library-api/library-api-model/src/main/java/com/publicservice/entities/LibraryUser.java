package com.publicservice.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_reader")
public class LibraryUser implements Serializable {

  @Id
  @Column(nullable = false, unique = true)
  @Size(max = 55)
  private String username;

  @NotNull
  @Size(max = 100)
  private String lastname;

  @NotNull
  private String firstname;

  @NotNull
  @Size(min = 5)
  private String password;

  @NotNull
  @Size(max = 100)
  private String address;

  @Size(max = 50)
  private String city;

  @NotNull
  @Size(max = 5)
  @Pattern(regexp = ".*/^(?:[0-9]\\d|9[0-8])\\d{3}$/")
  private String zipCode;

  @Email
  @Column(nullable = false, unique = true)
  @Size(max = 100)
  private String email;

  @DateTimeFormat
  private Date creationDate;

  @NotNull
  private Boolean active;

  @OneToMany(mappedBy = "userID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Borrow> borrows;
}
