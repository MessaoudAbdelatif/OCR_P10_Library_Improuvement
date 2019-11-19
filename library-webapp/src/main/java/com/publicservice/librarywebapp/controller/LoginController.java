package com.publicservice.librarywebapp.controller;

import com.publicservice.librarywebapp.bean.LibraryUserDto;
import com.publicservice.librarywebapp.proxy.MSLibraryApiProxy;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

  private final MSLibraryApiProxy msLibraryApiProxy;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;


  public LoginController(MSLibraryApiProxy msLibraryApiProxy
  ) {
    this.msLibraryApiProxy = msLibraryApiProxy;

  }

  @GetMapping("/login")
  public String login(Model model) {
    return "views/login";
  }


  @GetMapping("/loginConfirme")
  public String loginConfirme(Model model) {
    return "views/loginConfirme";
  }


  @GetMapping("/logout")
  public String logout(Model model) {
    return "views/logoutConfirme";
  }


  @GetMapping("/logoutConfirme")
  public String logoutConfirme(Model model) {
    return "views/logoutConfirme";
  }


  @GetMapping("/403")
  public String error403() {
    return "errors/403";
  }


  @GetMapping("/register")
  public String registerform(Model model) {
    model.addAttribute("libraryUser", new LibraryUserDto());
    return "views/register";
  }

  @PostMapping("/register")
  public String newUserLibrary(Model model,
      @ModelAttribute("libraryUser") @Valid LibraryUserDto libraryUserDto,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "views/register";
    }
    libraryUserDto.setPassword(bCryptPasswordEncoder.encode(libraryUserDto.getPassword()));
    msLibraryApiProxy.createNewUser(libraryUserDto);
    return "views/confirmRegister";
  }

}