package com.posexample.springexample.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record userRequest(String username,
                          String email,
                          String password
) {
}
