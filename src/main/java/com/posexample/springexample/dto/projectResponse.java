package com.posexample.springexample.dto;
import java.time.LocalDateTime;

public record projectResponse(Long Id,
                              String name,
                              LocalDateTime created_at,
                              userResponse user) {
}
