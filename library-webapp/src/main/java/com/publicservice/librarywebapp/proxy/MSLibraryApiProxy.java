package com.publicservice.librarywebapp.proxy;

import com.publicservice.librarywebapp.bean.BookDto;
import com.publicservice.librarywebapp.bean.BorrowDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MicroService-LibraryApiService", url = "localhost:9090")
public interface MSLibraryApiProxy {

  @GetMapping(value = "Borrows/{id}")
  BorrowDto findBorrowById(@PathVariable("id") Long id);

  @PostMapping(value = "Borrows/{id}/addExtraTime")
  void addExtraTime(@PathVariable("id") Long id);

  @PostMapping(value = "Borrows/newBorrow")
  BorrowDto createBorrow(@RequestBody BorrowDto newBorrowDto);

  @GetMapping(value = "Books/{id}")
  BookDto findOneBookById(@PathVariable("id") Long id);

  @GetMapping(value = "Users/{username}")
  List<BorrowDto> checkeLibraryUserBorrowedBook(@PathVariable("username") String username);

  }