package com.posexample.springexample.dto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record userResponse(Long Id,
                           String username,
                           String email,
                           LocalDateTime created_at) {
}
