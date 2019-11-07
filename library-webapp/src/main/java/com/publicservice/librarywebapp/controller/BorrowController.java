package com.publicservice.librarywebapp.controller;

import com.publicservice.librarywebapp.bean.BorrowDto;
import com.publicservice.librarywebapp.bean.LibraryUserDto;
import com.publicservice.librarywebapp.proxy.MSLibraryApiProxy;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BorrowController {

  private final MSLibraryApiProxy msLibraryApiProxy;

  public BorrowController(
      MSLibraryApiProxy msLibraryApiProxy) {
    this.msLibraryApiProxy = msLibraryApiProxy;
  }

  @GetMapping(value = {"/", "/index", "/home"})
  public String index() {
    return "views/Index";

  }

  @GetMapping(value = "/Borrows")
  public String borrows(Model model) {
    String username = "john33";
    LibraryUserDto libraryUserDto = msLibraryApiProxy.findOneLibraryUser(username);
    List<BorrowDto> borrowDtos = msLibraryApiProxy.checkeLibraryUserBorrowedBook(username);
    model.addAttribute("listOfBorrows", borrowDtos);
    model.addAttribute("User", libraryUserDto);
    return "views/borrows";
  }
}
