package com.publicservice.business.contract;


import static com.publicservice.business.contract.BookingValidatorBusiness.ValidationResult.ALREADY_BORROWING_THIS_BOOK;
import static com.publicservice.business.contract.BookingValidatorBusiness.ValidationResult.SUCCESS;

import com.publicservice.business.contract.BookingValidatorBusiness.ValidationResult;
import com.publicservice.entities.Booking;
import java.util.function.Function;

public interface BookingValidatorBusiness extends Function<Booking, ValidationResult> {

  static BookingValidatorBusiness isNotBorrowingThisBook() {
    return booking ->
        booking.getId().getLibraryUserID()
            .getBorrows()
            .stream()
            .noneMatch(borrow -> borrow.getBookID().getId().equals(booking.getId().getBookID().getId())) ? SUCCESS
            : ALREADY_BORROWING_THIS_BOOK;
  }

  default BookingValidatorBusiness and (BookingValidatorBusiness other){
    return booking -> {
      ValidationResult result = this.apply(booking);
      return result.equals(SUCCESS) ? other.apply(booking) : result;
    };
  }

  enum ValidationResult {
    SUCCESS,
    ALREADY_BORROWING_THIS_BOOK,
  }

}

