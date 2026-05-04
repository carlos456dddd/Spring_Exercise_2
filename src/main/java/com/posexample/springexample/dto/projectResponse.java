package com.posexample.springexample.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record projectResponse(Long Id,
                              String name,
                              String description,
                              Long IdUser,
                              LocalDateTime createdAt
                             ) {
}
