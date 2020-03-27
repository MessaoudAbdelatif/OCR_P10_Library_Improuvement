package com.publicservice.v1.dto.mapper;


import com.publicservice.entities.Booking;
import com.publicservice.v1.dto.model.BookingDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = BookingKeyMapper.class)
public interface BookingMapper {

  BookingDto toBookingDto(Booking booking);

  @Mapping(target = "isClosed", defaultValue = "false")
  Booking toBooking(BookingDto bookingDto);

}
