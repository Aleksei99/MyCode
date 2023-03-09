package com.smuraha.mycode.dao.repo;

import com.smuraha.mycode.dao.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByEmail(String email);
}
