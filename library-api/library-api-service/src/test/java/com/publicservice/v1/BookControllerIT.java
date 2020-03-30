package com.publicservice.v1;

import static org.assertj.core.api.Assertions.assertThat;

import com.publicservice.v1.dto.model.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:db_library.sql"})})
public class BookControllerIT {

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  public void homePage_getNumberOfBooksPerPage_thenSuccess() {
    BookDto parsedBook = restTemplate.getForObject("/books/1", BookDto.class);
    assertThat(parsedBook.getName()).isEqualTo("Da Vinci Code");
  }

  @Test
  void deleteBooking_thenSuccess() {
    restTemplate.postForObject("/booking/2/john33",Void.class,Void.class);

  }
}
