package com.nishchay.journalApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class GetUserResponse {
    private Long id;
    private String username;
    private String password;
    private LocalDateTime date;
}
