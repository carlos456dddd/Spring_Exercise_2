package com.posexample.springexample.dto;

import java.time.LocalDateTime;

public record taskRequest(Long Id,
                          String title,
                          String description) {
}
