package com.publicservice.librarywebapp.proxy;

import com.publicservice.librarywebapp.bean.BookBean;
import com.publicservice.librarywebapp.bean.BorrowBean;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "MicroService-LibraryApiService", url = "localhost:9090")
public interface MSLibraryApiProxy {

  @GetMapping(value = "Borrows/{id}")
  BorrowBean findBorrowById(@PathVariable("id") Long id);

  @PostMapping(value = "Borrows/{id}/addExtraTime")
  void addExtraTime(@PathVariable("id") Long id);

  @PostMapping(value = "Borrows/newBorrow")
  BorrowBean createBorrow(BorrowBean newBorrowBean);

  @GetMapping(value = "Books/{id}")
  BookBean findOneBookById(@PathVariable("id") Long id);

  @GetMapping(value = "Users/{username}")
  List<BorrowBean> checkeLibraryUserBorrowedBook(@PathVariable("username") String username);

  }