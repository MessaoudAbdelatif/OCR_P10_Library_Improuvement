package com.publicservice.librarybatch.proxy;

import com.publicservice.librarybatch.model.DelayBorrowUser;
import java.util.List;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "MicroService-LibraryApiService")
@RibbonClient(name = "MicroService-LibraryApiService")
public interface MSLibraryApiProxy {

  @GetMapping(value = "/delay/all")
  List<DelayBorrowUser> overTimeLimite();

  @GetMapping(value = "/booking/notification")
  List<DelayBorrowUser> notifyBookedUsed();


}
