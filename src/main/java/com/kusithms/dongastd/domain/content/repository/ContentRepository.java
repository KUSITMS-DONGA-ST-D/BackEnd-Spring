package com.kusithms.dongastd.domain.content.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusithms.dongastd.domain.content.entity.Content;

import java.time.LocalDateTime;
import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findAllByCreatedDateBeforeAndCategory(LocalDateTime start, String category);
    List<Content> findAllByCategory(String category);
}
