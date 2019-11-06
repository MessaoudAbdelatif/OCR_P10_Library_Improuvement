package com.publicservice.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Library implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(max = 50, min = 2)
  private String name;

  @NotNull
  @Size(max = 200)
  private String address;

  @Size(max = 50)
  private String city;

  @NotNull
  @Size(max = 5)
  @Pattern(regexp = ".*/^(?:[0-9]\\d|9[0-8])\\d{3}$/")
  private String zipCode;

  @Email
  @Size(max = 100)
  private String email;

  @Size(max = 45)
  private String phone;

  @OneToMany(mappedBy = "library", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Book> books;
}