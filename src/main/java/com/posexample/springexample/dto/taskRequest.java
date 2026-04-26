package com.posexample.springexample.dto;

import java.time.LocalDateTime;

public record taskRequest(String title,
                          String description) {
}
