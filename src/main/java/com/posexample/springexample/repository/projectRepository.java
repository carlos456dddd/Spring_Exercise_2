package com.posexample.springexample.repository;

import com.posexample.springexample.dto.projectResponse;
import com.posexample.springexample.model.Project;
import com.posexample.springexample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface projectRepository extends JpaRepository<Project, Long> {
    List<projectResponse> getProjectsByUser(User user);
}
