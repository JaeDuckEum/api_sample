package com.softsquared.template.src.app.domain.user;

import com.softsquared.template.config.BaseEntity;
import com.softsquared.template.src.app.domain.content.Content;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Getter
public class User extends BaseEntity {

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;

    @Builder.Default
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Content> contents = new ArrayList<>();
}
