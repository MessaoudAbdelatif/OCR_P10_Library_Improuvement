package com.publicservice.v1.dto.model;

import lombok.Data;

@Data
public class StockDto {

  protected Long id;
  protected Integer available;
  protected Integer outside;
  protected Integer total;
}
