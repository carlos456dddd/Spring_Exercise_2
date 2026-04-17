package com.posexample.springexample.repository;

import com.posexample.springexample.model.User;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface userRepository extends CrudRepository<User, Long> {

}
