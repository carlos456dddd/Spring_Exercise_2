package com.posexample.springexample.repository;

import com.posexample.springexample.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface projectRepository extends JpaRepository<Project, Long> {
}
