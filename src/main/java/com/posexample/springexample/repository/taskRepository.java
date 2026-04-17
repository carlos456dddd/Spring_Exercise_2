package com.posexample.springexample.repository;

import com.posexample.springexample.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface taskRepository extends JpaRepository<Task, Long> {

}
