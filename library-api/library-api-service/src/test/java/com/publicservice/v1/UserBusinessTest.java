package com.publicservice.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.publicservice.consumer.UserDao;
import com.publicservice.entities.LibraryUser;
import com.publicservice.v1.exception.LibraryUserNotFoundException;
import com.publicservice.v1.impl.UserBusinessImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserBusinessTest {

  @InjectMocks
  UserBusinessImpl classUnderTest;
  @Mock
  UserDao userDao;

  @Test
  void oneLibraryUser_thenSuccess() throws LibraryUserNotFoundException {
    LibraryUser libraryUser = new LibraryUser();
    when(userDao.findById("test")).thenReturn(Optional.of(libraryUser));

    classUnderTest.oneLibraryUser("test");

    verify(userDao).findById("test");
    assertThat(classUnderTest.oneLibraryUser("test")).isEqualTo(libraryUser);
  }

  @Test
  void oneLibraryUser_thenTrigger_LibraryUserNotFoundException() {
    when(userDao.findById("test")).thenReturn(Optional.ofNullable(null));

    assertThrows(LibraryUserNotFoundException.class,
        () -> classUnderTest.oneLibraryUser("test"));
  }

  @Test
  void saveOneLibraryUser_thenSuccess() {
    LibraryUser libraryUser = new LibraryUser();
    when(userDao.save(libraryUser)).thenReturn(libraryUser);

    classUnderTest.saveOneLibraryUser(libraryUser);

    verify(userDao).save(libraryUser);
  }
}
