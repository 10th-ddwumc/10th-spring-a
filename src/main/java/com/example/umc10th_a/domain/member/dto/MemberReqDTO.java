package com.example.umc10th_a.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberReqDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Join {
        private String email;
        private String password;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Login {
        private String email;
        private String password;
    }
}