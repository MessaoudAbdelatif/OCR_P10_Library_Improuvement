package com.publicservice.v1.dto.mapper;

import com.publicservice.entities.Borrow;
import com.publicservice.v1.dto.model.BorrowDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BorrowMapper {

//  @Mapping(target = "userID", source = "userID")
////  @Mapping(target = "bookID", source = "book.id")
//  BorrowDto toBorrowDto(Borrow borrow);
//
//  @Mapping(target = "username",source ="userID" )
////  @Mapping(target = "book.id", source = "bookID")
//  Borrow toBorrow(BorrowDto borrowDto);

}
