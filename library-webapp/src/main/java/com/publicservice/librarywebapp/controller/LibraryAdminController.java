package com.publicservice.librarywebapp.controller;

import com.publicservice.librarywebapp.bean.BorrowDto;
import com.publicservice.librarywebapp.proxy.MSLibraryApiProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryAdminController {

  private final MSLibraryApiProxy msLibraryApiProxy;

  public LibraryAdminController(
      MSLibraryApiProxy msLibraryApiProxy) {
    this.msLibraryApiProxy = msLibraryApiProxy;
  }

  @PostMapping(value = "borrows/ADMIN/newBorrow")
  BorrowDto createBorrow(BorrowDto newBorrowDto) {
    msLibraryApiProxy.createBorrow(newBorrowDto);
    return newBorrowDto;
  }

  @GetMapping(value = "borrows/ADMIN/{id}/close")
  void closeBorrow(@PathVariable("id") Long id) {
    msLibraryApiProxy.closeBorrow(id);
  }
}
