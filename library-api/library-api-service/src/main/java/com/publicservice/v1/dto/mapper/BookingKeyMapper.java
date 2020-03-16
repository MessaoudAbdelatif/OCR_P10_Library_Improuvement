package com.publicservice.v1.dto.mapper;

import com.publicservice.entities.BookingKey;
import com.publicservice.v1.dto.model.BookingKeyDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookingKeyMapper {

  @Mapping(target = "libraryUserID",source = "libraryUserID.username")
  @Mapping(target = "bookID",source = "bookID.id")
  BookingKeyDto toBookingKeyDto(BookingKey bookingKey);

  @Mapping(target = "libraryUserID.username",source = "libraryUserID")
  @Mapping(target = "bookID.id",source = "bookID")
  BookingKey toBookingKey(BookingKeyDto bookingKeyDto);

}
