package com.publicservice.librarywebapp.controller;

import com.publicservice.librarywebapp.bean.BorrowDto;
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

  @GetMapping(value = "/")
    public String index(Model model){
      String username = "messaoud";
      List<BorrowDto> borrowDtos =  msLibraryApiProxy.checkeLibraryUserBorrowedBook(username);
      model.addAttribute(borrowDtos);
      return "views/Index";
    }
}
