package com.posexample.springexample.dto;
import java.time.LocalDateTime;

public record taskResponse(Long id,
                           String title,
                           String description,
                           String status,
                           String priority,
                           userResponse userResponses,
                           projectResponse projectResponses,
                           LocalDateTime created_at) {
}
