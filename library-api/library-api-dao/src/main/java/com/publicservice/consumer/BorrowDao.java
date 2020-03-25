package com.publicservice.consumer;

import com.publicservice.entities.Book;
import com.publicservice.entities.Borrow;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BorrowDao extends JpaRepository<Borrow, Long> {

    /**
     * find the borrows that is over the date limite and still open not yet closed by the library admin.
     * @param date : using this assigned date in order to filtre the borrows.
     * @return List Borrow.
     * */
    List<Borrow> findBorrowByDateEndBeforeAndClosedFalse(Date date);
    Optional<List<Borrow>> findByBookIDOrderByDateEndAsc(Book book);
}
