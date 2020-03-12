package com.publicservice.business.contract;

import com.publicservice.entities.Booking;

public interface BookingBusiness {

  /**
   * When user make new booking.
   *
   * @param newBooking is the new booking entity.
   * @return new Borrow instance.
   */
  Booking createBooking(Booking newBooking) throws Exception;

}
