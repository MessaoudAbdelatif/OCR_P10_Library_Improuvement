package com.publicservice.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BookingKey implements Serializable {

  private LibraryUser libraryUserID;
  private Book bookID;

}
