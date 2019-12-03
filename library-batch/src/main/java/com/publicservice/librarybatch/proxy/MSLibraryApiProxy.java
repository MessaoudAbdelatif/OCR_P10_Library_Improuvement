package com.publicservice.librarybatch.proxy;

import com.publicservice.librarybatch.model.BorrowDto;
import com.publicservice.librarybatch.model.LibraryUserDto;
import java.util.List;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MicroService-LibraryApiService")
@RibbonClient(name = "MicroService-LibraryApiService")
public interface MSLibraryApiProxy {

  @GetMapping(value = "users/{username}")
  LibraryUserDto findOneLibraryUser(@PathVariable("username") String username);

  @GetMapping(value = "/delay")
  List<BorrowDto> borrowsOverTimeLimite();
}
