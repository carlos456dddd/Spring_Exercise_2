package com.posexample.springexample.dto;
import java.time.LocalDateTime;

public record taskResponse(Long id,
                           String title,
                           String description,
                           String status,
                           LocalDateTime created_at) {
}
