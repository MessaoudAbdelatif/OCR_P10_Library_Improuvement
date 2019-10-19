package com.publicservice.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Stock implements Serializable {

  @Id
  @OneToOne(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Long bookID;
  private Integer available;
  private Integer outside;
  private Integer total;


}
