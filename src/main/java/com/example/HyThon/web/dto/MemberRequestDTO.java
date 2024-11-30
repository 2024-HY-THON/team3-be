package com.example.HyThon.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

public class MemberRequestDTO {

    @Getter
    public static class MemberSignupDTO {

        @NotNull(message = "유저 이름은 필수 입력 값입니다.")
        @Length(min = 1, max = 30)
        private String name;

        @NotNull(message = "비밀번호는 필수 입력 값입니다.")
        @Length(min = 1, max = 100)
        private String password;
    }
}
