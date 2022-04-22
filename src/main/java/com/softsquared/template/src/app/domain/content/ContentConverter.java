package com.softsquared.template.src.app.domain.content;

import com.softsquared.template.src.app.domain.user.User;

import java.time.format.DateTimeFormatter;

public class ContentConverter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static ContentDTO.findRes convert(Content content, User user){
        return ContentDTO.findRes.builder()
                .contentId(content.getId())
                .title(content.getTitle())
                .content(content.getContent())
                .createdAt(content.getCreatedAt().format(DATE_TIME_FORMATTER))
                .updatedAt(content.getUpdatedAt().format(DATE_TIME_FORMATTER))
                .nickname(user.getNickname())
                .build();
    }
}
