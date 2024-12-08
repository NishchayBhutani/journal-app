package com.nishchay.journalApp.builder;

import com.nishchay.journalApp.dto.GetUserResponse;
import com.nishchay.journalApp.entity.User;

public class GetUserResponseBuilder {

    public static GetUserResponse build(User user) {
        return GetUserResponse
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .date(user.getDate())
                .build();
    }
}
