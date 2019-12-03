package com.publicservice.librarybatch.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reminder {
  @NotNull
  private String name;

  @NotNull
  @Email
  private String email;

  @NotNull
  private String booktitle;

  @NotNull
  @Min(10)
  private String feedback;

}
