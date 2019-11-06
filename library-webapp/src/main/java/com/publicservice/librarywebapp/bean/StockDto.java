package com.publicservice.librarywebapp.bean;

import lombok.Data;

@Data
public class StockDto {

  protected Long id;
  protected Integer available;
  protected Integer outside;
  protected Integer total;
}

