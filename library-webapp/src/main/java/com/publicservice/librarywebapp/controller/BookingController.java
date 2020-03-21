package com.publicservice.librarywebapp.controller;

import com.publicservice.librarywebapp.configuration.WebApplicationPropertiesConfiguration;
import com.publicservice.librarywebapp.proxy.MSLibraryApiProxy;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

  private final MSLibraryApiProxy msLibraryApiProxy;
  private final WebApplicationPropertiesConfiguration webApplicationPropertiesConfiguration;


  public BookingController(MSLibraryApiProxy msLibraryApiProxy,
      WebApplicationPropertiesConfiguration webApplicationPropertiesConfiguration) {
    this.msLibraryApiProxy = msLibraryApiProxy;
    this.webApplicationPropertiesConfiguration = webApplicationPropertiesConfiguration;
  }


}
