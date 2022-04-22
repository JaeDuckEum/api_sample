package com.softsquared.template.src.app.domain.content;

import com.softsquared.template.config.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content,Long> {
    List<Content> findAllByState(State state);
    Optional<Content> findById(Long id);
}
