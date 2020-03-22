package com.publicservice.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking implements Serializable {

  @EmbeddedId
  private BookingKey id;

  @NotNull
  @Temporal(TemporalType.DATE)
  private Date dateCreation;

  @NotNull
  private Boolean isClosed;

  @Temporal(TemporalType.DATE)
  private Date dateOfClosing;
}
