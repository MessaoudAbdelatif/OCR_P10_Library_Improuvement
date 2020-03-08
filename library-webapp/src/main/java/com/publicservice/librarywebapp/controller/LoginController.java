package com.publicservice.librarywebapp.controller;

import com.publicservice.librarywebapp.bean.LibraryUserDto;
import com.publicservice.librarywebapp.configuration.CookieUtil;
import com.publicservice.librarywebapp.proxy.MSLibraryApiProxy;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  private final MSLibraryApiProxy msLibraryApiProxy;
  private final CookieUtil cookieUtil;

  public LoginController(MSLibraryApiProxy msLibraryApiProxy,
      CookieUtil cookieUtil) {
    this.msLibraryApiProxy = msLibraryApiProxy;

    this.cookieUtil = cookieUtil;
  }

  @GetMapping("/login")
  public String login(Model model) {
    return "views/login";
  }


  @GetMapping("/loginConfirme")
  public String loginConfirme(Model model) {
    return "views/loginConfirme";
  }


  @GetMapping("/logout1")
  public String logout(Model model, HttpServletResponse response) {
    cookieUtil.clear(response, "JWTtoken");
    return "views/index";
  }


  @GetMapping("/logoutConfirme")
  public String logoutConfirme(Model model) {
    return "views/logoutConfirme";
  }


  @GetMapping("/403")
  public String error403() {
    return "errors/403";
  }


  @GetMapping("/register/")
  public String registerform(Model model) {
    model.addAttribute("libraryUser", new LibraryUserDto());
    return "views/register";
  }

//  @PostMapping("/register")
////  public String newUserLibrary(Model model,
////      @ModelAttribute("libraryUser") @Valid LibraryUserDto libraryUserDto,
////      BindingResult bindingResult) {
////    if (bindingResult.hasErrors()) {
////      return "views/register";
////    }
////    libraryUserDto.setPassword(bCryptPasswordEncoder.encode(libraryUserDto.getPassword()));
////    msLibraryApiProxy.createNewUser(libraryUserDto);
////    return "views/confirmRegister";
////  }
}