package com.publicservice.librarywebapp.controller;

import com.publicservice.librarywebapp.bean.LibarayUserBorrowInfoDto;
import com.publicservice.librarywebapp.bean.LibraryUserDto;
import com.publicservice.librarywebapp.configuration.CookieUtil;
import com.publicservice.librarywebapp.proxy.MSLibraryApiProxy;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class BookingController {

  private final MSLibraryApiProxy msLibraryApiProxy;
  private CookieUtil cookieUtil;


  public BookingController(MSLibraryApiProxy msLibraryApiProxy,
      CookieUtil cookieUtil) {
    this.msLibraryApiProxy = msLibraryApiProxy;
    this.cookieUtil = cookieUtil;
  }

  @GetMapping(value = "/booking")
  public String booking(Model model, HttpServletRequest req) {
    String cookie = cookieUtil.cookieValue(req, "JWTtoken");
    String username = cookieUtil.usernameFromJWT(cookie);
    LibraryUserDto user = msLibraryApiProxy.findOneLibraryUser(username);
    List<LibarayUserBorrowInfoDto> lUBID = msLibraryApiProxy.libraryUserBorrowsInfo(username);
    model.addAttribute("lUBID", lUBID);
    model.addAttribute("User", user);
    return "views/booking";
  }
}
