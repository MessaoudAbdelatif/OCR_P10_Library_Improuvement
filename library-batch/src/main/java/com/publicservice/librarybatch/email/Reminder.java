package com.publicservice.librarybatch.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Reminder {
  @NotNull
  private String name;

  @NotNull
  @Email
  private String email;

  @NotNull
  @Min(10)
  private String feedback;

}
