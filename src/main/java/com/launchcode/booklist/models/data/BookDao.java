package com.launchcode.booklist.models.data;

import com.launchcode.booklist.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookDao extends CrudRepository<Book, Integer> {
    List<Book> findByUserId(Integer userId);


}
