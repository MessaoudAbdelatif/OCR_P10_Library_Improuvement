package com.publicservice.business.contract;


import static com.publicservice.business.contract.BookingValidatorBusiness.ValidationResult.ALREADY_BORROWING_THIS_BOOK;
import static com.publicservice.business.contract.BookingValidatorBusiness.ValidationResult.SUCCESS;

import com.publicservice.business.contract.BookingValidatorBusiness.ValidationResult;
import com.publicservice.entities.Book;
import com.publicservice.entities.LibraryUser;
import java.util.function.BiFunction;

public interface BookingValidatorBusiness extends BiFunction<LibraryUser, Book, ValidationResult> {

  static BookingValidatorBusiness isNotBorrowingThisBook() {
    return (libraryUser,book) ->
        libraryUser.getBorrows()
            .stream()
            .noneMatch(
                borrow -> borrow.getBookID().getId().equals(book.getId()))
            ? SUCCESS
            : ALREADY_BORROWING_THIS_BOOK;
  }

  default BookingValidatorBusiness and(BookingValidatorBusiness other) {
    return (libraryUser,book) -> {
      ValidationResult result = this.apply(libraryUser,book);
      return result.equals(SUCCESS) ? other.apply(libraryUser,book) : result;
    };
  }

  enum ValidationResult {
    SUCCESS,
    ALREADY_BORROWING_THIS_BOOK,
  }

}

