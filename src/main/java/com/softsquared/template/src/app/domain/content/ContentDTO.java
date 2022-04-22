package com.softsquared.template.src.app.domain.content;

import com.softsquared.template.src.app.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class ContentDTO {

    @Builder
    @AllArgsConstructor
    @Getter
    public static class findRes {
        private Long contentId;
        private String title;
        private String content;
        private String createdAt;
        private String updatedAt;
        private String nickname;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class createReq{
        private Long userId;
        private String title;
        private String content;

        public Content contentBuild(User user){
            return Content.builder()
                    .user(user)
                    .title(this.title)
                    .content(this.content)
                    .build();
        }
    }

    @AllArgsConstructor
    @Getter
    public static class createRes{
        private Long contentId;
        private String title;
    }
}
