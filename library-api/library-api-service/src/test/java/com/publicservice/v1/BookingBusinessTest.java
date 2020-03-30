package com.publicservice.v1;


import static org.mockito.Mockito.verify;

import com.publicservice.consumer.BookingDao;
import com.publicservice.entities.Booking;
import com.publicservice.entities.BookingKey;
import com.publicservice.entities.LibraryUser;
import com.publicservice.v1.impl.BookingBusinessImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookingBusinessTest {

  @InjectMocks
  BookingBusinessImpl classUnderTest;
  @Mock
  BookingDao bookingDao;

  @Test
  public void getBookingById_thenSuccess() {
    BookingKey bookingKey = new BookingKey();
    classUnderTest.getBookingById(bookingKey);

    verify(bookingDao).getOne(bookingKey);
  }

  @Test
  public void getBookingByUserID_thenSuccess() {
    LibraryUser foundedUser = new LibraryUser();
    classUnderTest.getBookingByUserID(foundedUser);

    verify(bookingDao).findBookingByIdLibraryUserID(foundedUser.getUsername());
  }

  @Test
  public void deleteBookingById_thenSuccess() {
    BookingKey bookingKey = new BookingKey();
    classUnderTest.deleteBookingById(bookingKey);

    verify(bookingDao).deleteById(bookingKey);
  }

  @Test
  public void updateBookingInfo_thenSuccess() {
    Booking foundedBooking = new Booking();
    classUnderTest.updateBookingInfo(foundedBooking);

    verify(bookingDao).save(foundedBooking);
  }

}
