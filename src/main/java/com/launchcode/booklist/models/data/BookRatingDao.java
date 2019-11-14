package com.launchcode.booklist.models.data;

import com.launchcode.booklist.models.BookRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BookRatingDao extends CrudRepository<BookRating, Integer> {
}
