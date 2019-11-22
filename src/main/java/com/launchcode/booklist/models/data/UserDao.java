package com.launchcode.booklist.models.data;

import com.launchcode.booklist.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {
    @Query()
    public List<User> findByUsername(String username);

}
