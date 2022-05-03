package com.softsquared.template.src.app.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDTO {

    /**
     * 회원가입 request
     * */
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

    /**
     * 회원가입 response
     * */
    @AllArgsConstructor
    @Getter
    public static class createRes {
        private Long userId;
        private String email;
    }

    /**
     * 로그인 request
     * */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class loginReq{
        private String email;
        private String password;
    }

    /**
     * 로그인 response
     * */
    @AllArgsConstructor
    @Getter
    public static class loginRes {
        private Long userId;
        private String nickname;
        private String token;
    }

    /**
     * JWT response
     * */
    @AllArgsConstructor
    @Getter
    public static class getUserInfoRes {
        private Long userId;
        private String nickname;
    }
}
