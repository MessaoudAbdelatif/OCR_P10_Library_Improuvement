package com.publicservice.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Borrow implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne
  @JoinColumn(name = "user_id")
  private String userID;
  @OneToOne
  @JoinColumn(name = "book_id")
  private Long bookID;
  @DateTimeFormat
  private Date dateStart;
  @DateTimeFormat
  private Date dateEnd;
  private Boolean extraTime;
}
