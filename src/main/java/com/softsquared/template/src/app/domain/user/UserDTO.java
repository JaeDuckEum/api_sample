package com.softsquared.template.src.app.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class createReq{
        private String email;
        private String password;
        private String nickname;

        public User buildUser(PasswordEncoder passwordEncoder){
            return User.builder()
                    .email(this.email)
                    .nickname(this.nickname)
                    .password(passwordEncoder.encode(this.password))
                    .build();
        }
    }

    @AllArgsConstructor
    @Getter
    public static class createRes {
        private Long userId;
        private String email;
    }
}
