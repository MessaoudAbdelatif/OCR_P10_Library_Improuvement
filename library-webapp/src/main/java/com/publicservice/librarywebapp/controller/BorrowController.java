package com.publicservice.librarywebapp.controller;

import com.publicservice.librarywebapp.bean.BorrowDto;
import com.publicservice.librarywebapp.proxy.MSLibraryApiProxy;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BorrowController {

    @Autowired
    private MSLibraryApiProxy msLibraryApiProxy;

    @GetMapping(value = "/")
    public String Accueil(Model model){
      String username = "messaoud";
      List<BorrowDto> borrowDtos =  msLibraryApiProxy.checkeLibraryUserBorrowedBook(username);
      model.addAttribute(borrowDtos);
      return "views/Accueil";
    }
}
