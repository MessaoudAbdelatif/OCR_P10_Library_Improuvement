package com.publicservice.v1.dto.mapper;

import com.publicservice.entities.Book;
import com.publicservice.v1.dto.model.BookDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {

  @Mapping(target = "library.id", source = "library")
//  @Mapping(target = "stock.stockId", source = "stockId")
  Book toBook(BookDto bookDto);

  @Mapping(target = "library", source = "library.id")
//  @Mapping(target = "stockId", source = "stock.stockId")
  BookDto toBookDto(Book book);
}
