package com.posexample.springexample.dto;
import java.time.LocalDateTime;

public record userResponse(Long Id,
                           String username,
                           LocalDateTime created_at) {
}
