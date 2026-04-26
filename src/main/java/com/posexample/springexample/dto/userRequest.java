package com.posexample.springexample.dto;

import java.time.LocalDateTime;

public record userRequest(String username,
                          String email,
                          String password
) {
}
