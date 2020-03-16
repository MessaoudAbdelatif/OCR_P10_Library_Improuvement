package com.publicservice.v1.dto.mapper;

import com.publicservice.entities.Booking;
import com.publicservice.v1.dto.model.BookingDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = BookingKeyMapper.class)
public interface BookingMapper {

  BookingDto toBookingDto(Booking booking);

  Booking toBooking(BookingDto bookingDto);

}
