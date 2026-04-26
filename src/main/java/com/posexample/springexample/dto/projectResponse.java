package com.posexample.springexample.dto;

import java.time.LocalDateTime;

public record projectResponse(Long Id,
                              String name,
                              String description,
                              LocalDateTime created_at,
                              Long Id_user) {
}
