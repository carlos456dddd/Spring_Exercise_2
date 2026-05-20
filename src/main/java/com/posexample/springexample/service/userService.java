package com.posexample.springexample.service;

import com.posexample.springexample.dto.userRequest;
import com.posexample.springexample.dto.userResponse;
import com.posexample.springexample.model.User;
import com.posexample.springexample.repository.userRepository;
import jakarta.persistence.*;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class userService {
    private final userRepository userRepo;

    public userService(userRepository userRepo) {
        this.userRepo = userRepo;
    }

    public userResponse createUser(@NonNull userRequest user) {

        User java = User.builder()
                .username(user.username())
                .email(user.email())
                .password(user.password()).build();

        User a = userRepo.save(java);
        return userResponse.builder()
                .Id(a.getId())
                .username(a.getUsername())
                .email(a.getEmail())
                .created_at(a.getCreatedAt())
                .build();
    }

    public List<userResponse> findAll() {
        List<userResponse> a = new ArrayList<>();

        userRepo.findAll().iterator().forEachRemaining(e -> a.add(new userResponse(e.getId(), e.getUsername(), e.getEmail(), e.getCreatedAt())));

        return a;
    }

    public userResponse findById(Long id) {
        return userRepo.findById(id)
                .map(this::toFindUser)
                .orElseThrow(() -> new UsernameNotFoundException("Not id"));
    }

    private userResponse toFindUser(@NonNull User user) {
        return userResponse.builder()
                .Id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .created_at(user.getCreatedAt())
                .build();
    }


}
