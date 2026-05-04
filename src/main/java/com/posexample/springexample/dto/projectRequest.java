package com.posexample.springexample.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public record projectRequest(
        Long idUser,
        String name,
        String description) {
}
