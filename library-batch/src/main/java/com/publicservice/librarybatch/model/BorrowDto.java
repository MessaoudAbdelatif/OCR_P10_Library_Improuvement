package com.publicservice.librarybatch.model;

import java.util.Date;
import lombok.Data;

@Data
public class BorrowDto {

    protected Long id;
    protected String username;
    protected Long bookId;
    protected Date dateStart;
    protected Date dateEnd;
    protected Boolean extraTime;
    protected Boolean closed;
}
