package com.publicservice.librarywebapp.controller;

import com.publicservice.librarywebapp.bean.BookDto;
import com.publicservice.librarywebapp.proxy.MSLibraryApiProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

  @Autowired
  private MSLibraryApiProxy msLibraryApiProxy;

  @GetMapping("/Books")
  public String lookingForABook(Model model,
      @RequestParam(name = "numPages", defaultValue = "0") int numPages,
      @RequestParam(name = "keyword", defaultValue = "") String keyword,
      @RequestParam(name = "kindOfSearch", defaultValue = "NAME") String kindOfSearch) {
    try {
      Page<BookDto> bookDtoPage = msLibraryApiProxy
          .lookingForABook(numPages, 5, keyword, kindOfSearch);
      model.addAttribute("bookDto", bookDtoPage.getContent());
      model.addAttribute("pages", new int[bookDtoPage.getTotalPages()]);
      model.addAttribute("nbrPagesTotal", new int[bookDtoPage.getTotalPages()].length);
      model.addAttribute("currentPage", numPages);
      model.addAttribute("kindOfSearch", kindOfSearch);
      model.addAttribute("keyword", keyword);
    } catch (Exception e) {
      model.addAttribute("exceptionBookNotFound", e);
    }
    return "views/Books";
  }
}
