package com.posexample.springexample.dto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskResponse(Long id,
                           String title,
                           String description,
                           String status,
                           projectResponse project,
                           userResponse user,
                           LocalDateTime created_at) {


}
