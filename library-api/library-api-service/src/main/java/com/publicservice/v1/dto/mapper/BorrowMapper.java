package com.publicservice.v1.dto.mapper;

import com.publicservice.entities.Borrow;
import com.publicservice.v1.dto.model.BorrowDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BorrowMapper {

  @Mapping(target = "username", source = "userID.username")
  @Mapping(target = "bookId", source = "bookID.id")
  BorrowDto toBorrowDto(Borrow borrow);

  @Mapping(target = "userID.username",source ="username" )
  @Mapping(target = "bookID.id", source = "bookId")
  Borrow toBorrow(BorrowDto borrowDto);

}
