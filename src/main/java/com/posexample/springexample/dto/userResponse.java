package com.posexample.springexample.dto;
import java.time.LocalDateTime;

public record userResponse(Long Id,
                           String username,
                           String email,
                           LocalDateTime created_at) {
}
