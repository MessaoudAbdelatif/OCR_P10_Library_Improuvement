package com.publicservice.librarywebapp.proxy;

import com.publicservice.librarywebapp.bean.BookDto;
import com.publicservice.librarywebapp.bean.BookPageDto;
import com.publicservice.librarywebapp.bean.BorrowDto;
import com.publicservice.librarywebapp.bean.LibraryUserDto;
import com.publicservice.librarywebapp.bean.StockDto;
import java.util.List;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "MicroService-LibraryApiService")
@RibbonClient(name = "MicroService-LibraryApiService")
public interface MSLibraryApiProxy {

  @GetMapping(value = "Borrows/{id}")
  BorrowDto findBorrowById(@PathVariable("id") Long id);

  @PostMapping(value = "Borrows/{id}/addExtraTime")
  void addExtraTime(@PathVariable("id") Long id);

  @PostMapping(value = "Borrows/newBorrow")
  BorrowDto createBorrow(@RequestBody BorrowDto newBorrowDto);

  @GetMapping(value = "Books/{id}")
  BookDto findOneBookById(@PathVariable("id") Long id);

  @GetMapping(value = "Books/{id}/Stocks")
  StockDto findStockByBookId(@PathVariable("id") Long id);

  @GetMapping(value = "Users/{username}/Borrows")
  List<BorrowDto> checkeLibraryUserBorrowedBook(@PathVariable("username") String username);

  @GetMapping(value = "Users/{username}")
  LibraryUserDto findOneLibraryUser(@PathVariable("username") String username);

  @GetMapping(value = "Books/search")
  BookPageDto lookingForABook(@RequestParam(value = "page",defaultValue = "0") int numPage, @RequestParam(value = "size", defaultValue = "5") int size,
      @RequestParam(value = "keyword", defaultValue = "") String keyword, @RequestParam(value = "kindOfSearch", defaultValue = "NAME") String kindOfSearch);


  }