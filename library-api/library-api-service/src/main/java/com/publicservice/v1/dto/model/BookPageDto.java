package com.publicservice.v1.dto.model;

import java.util.List;
import lombok.Data;

@Data
public class BookPageDto {

  List<BookDto> booksDto;
  Integer size;
  Integer page;
  int[] totalPages;
  int nbrTotalPages;
  String keyword;
  String kindOfSearch;

}
