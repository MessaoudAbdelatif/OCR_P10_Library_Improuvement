package com.publicservice.librarywebapp.bean;

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
