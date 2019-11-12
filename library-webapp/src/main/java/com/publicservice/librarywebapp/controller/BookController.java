package com.publicservice.librarywebapp.controller;

import com.publicservice.librarywebapp.bean.BookDto;
import com.publicservice.librarywebapp.bean.BookPageDto;
import com.publicservice.librarywebapp.bean.StockDto;
import com.publicservice.librarywebapp.configuration.WebApplicationPropertiesConfiguration;
import com.publicservice.librarywebapp.proxy.MSLibraryApiProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

  private final MSLibraryApiProxy msLibraryApiProxy;
  private final WebApplicationPropertiesConfiguration webApplicationPropertiesConfiguration;

  public BookController(
      MSLibraryApiProxy msLibraryApiProxy,
      WebApplicationPropertiesConfiguration webApplicationPropertiesConfiguration) {
    this.msLibraryApiProxy = msLibraryApiProxy;
    this.webApplicationPropertiesConfiguration = webApplicationPropertiesConfiguration;
  }

  @GetMapping("/Books")
  public String lookingForABook(Model model,
      @RequestParam(name = "page", defaultValue = "0") int numPages,
      @RequestParam(name = "keyword", defaultValue = "") String keyword,
      @RequestParam(name = "kindOfSearch", defaultValue = "NAME") String kindOfSearch) {
    int sizeDefaultPage = webApplicationPropertiesConfiguration.getSizeDefaultPage();
    try {
      BookPageDto bookPageDto = msLibraryApiProxy
          .lookingForABook(numPages, sizeDefaultPage, keyword, kindOfSearch);
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

  @GetMapping("/Books/{id}")
  public String bookDetails(Model model, @PathVariable("id") Long id) {
    BookDto bookDto = msLibraryApiProxy.findOneBookById(id);
    StockDto stockDto = msLibraryApiProxy.findStockByBookId(id);
    model.addAttribute("book", bookDto);
    model.addAttribute("stock", stockDto);
    return "views/booksDetails";
  }


}
