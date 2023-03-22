package com.kusithms.dongastd.domain.contentdata.repository;

import com.kusithms.dongastd.domain.content.entity.Content;
import com.kusithms.dongastd.domain.contentdata.entity.ContentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ContentDataRepository extends JpaRepository<ContentData, Long> {
    ContentData findByContent(Content content);
    List<ContentData> findAllByCreatedDateBetweenAndContent(LocalDateTime start, LocalDateTime end, Content content);
    List<ContentData> findByContent_CreatedDateBetween(LocalDateTime start, LocalDateTime end);
}
