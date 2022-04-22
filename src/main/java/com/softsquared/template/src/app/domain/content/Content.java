package com.softsquared.template.src.app.domain.content;

import com.softsquared.template.config.BaseEntity;
import com.softsquared.template.src.app.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="content")
public class Content extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",nullable = false)
    private User user;

    @Column(nullable = false,length = 100)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

}
