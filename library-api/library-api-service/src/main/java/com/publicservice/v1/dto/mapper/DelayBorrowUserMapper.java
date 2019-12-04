package com.publicservice.v1.dto.mapper;

import com.publicservice.entities.Book;
import com.publicservice.entities.Borrow;
import com.publicservice.entities.LibraryUser;
import com.publicservice.v1.dto.model.DelayBorrowUser;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DelayBorrowUserMapper {

  DelayBorrowUser toDelayBorrowUserController(Borrow borrow, LibraryUser libraryUser,
      Book book);

}
