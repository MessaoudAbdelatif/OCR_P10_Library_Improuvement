package com.publicservice.librarywebapp.controller;

import com.publicservice.librarywebapp.bean.BookPageDto;
import com.publicservice.librarywebapp.proxy.MSLibraryApiProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

  private final MSLibraryApiProxy msLibraryApiProxy;

  public BookController(
      MSLibraryApiProxy msLibraryApiProxy) {
    this.msLibraryApiProxy = msLibraryApiProxy;
  }

  @GetMapping("/Books")
  public String lookingForABook(Model model,
      @RequestParam(name = "page", defaultValue = "0") int numPages,
      @RequestParam(name = "keyword", defaultValue = "") String keyword,
      @RequestParam(name = "kindOfSearch", defaultValue = "NAME") String kindOfSearch) {
    try {
     BookPageDto bookPageDto = msLibraryApiProxy
          .lookingForABook(numPages, 2, keyword, kindOfSearch);
      model.addAttribute("bookDto", bookPageDto.getBooksDto());
      model.addAttribute("pages", bookPageDto.getTotalPages());
      model.addAttribute("nbrPagesTotal", bookPageDto.getNbrTotalPages());
      model.addAttribute("currentPage", numPages);
      model.addAttribute("kindOfSearch", kindOfSearch);
      model.addAttribute("keyword", keyword);
    } catch (Exception e) {
      model.addAttribute("exceptionBookNotFound", e);
    }
    return "views/books";
  }


}
