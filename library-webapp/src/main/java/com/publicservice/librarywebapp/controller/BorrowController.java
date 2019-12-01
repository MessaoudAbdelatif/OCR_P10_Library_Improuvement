package com.publicservice.librarywebapp.controller;

import com.publicservice.librarywebapp.bean.BorrowDto;
import com.publicservice.librarywebapp.bean.LibraryUserDto;
import com.publicservice.librarywebapp.configuration.CookieUtil;
import com.publicservice.librarywebapp.proxy.MSLibraryApiProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BorrowController {

  private final MSLibraryApiProxy msLibraryApiProxy;
  private CookieUtil cookieUtil;

  public BorrowController(
      MSLibraryApiProxy msLibraryApiProxy,
      CookieUtil cookieUtil) {
    this.msLibraryApiProxy = msLibraryApiProxy;
    this.cookieUtil = cookieUtil;
  }

  @GetMapping(value = {"/", "/index", "/home"})
  public String index() {
    return "views/Index";
  }

  @GetMapping(value = "/Borrows")
  public String borrows(Model model, HttpServletRequest req) {
    String cookie = cookieUtil.cookieValue(req,"JWTtoken");
    String username = cookieUtil.usernameFromJWT(cookie);
    LibraryUserDto libraryUserDto = msLibraryApiProxy.findOneLibraryUser(username);
    List<BorrowDto> borrowDtos = msLibraryApiProxy.checkeLibraryUserBorrowedBook(username);
    model.addAttribute("listOfBorrows", borrowDtos);
    model.addAttribute("User", libraryUserDto);
    return "views/borrows";
  }
}
