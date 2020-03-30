package com.publicservice.v1.dto.mapper;

import com.publicservice.entities.BookingKey;
import com.publicservice.v1.dto.model.BookingKeyDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookingKeyMapper {

  BookingKeyDto toBookingKeyDto(BookingKey bookingKey);

  BookingKey toBookingKey(BookingKeyDto bookingKeyDto);
}
