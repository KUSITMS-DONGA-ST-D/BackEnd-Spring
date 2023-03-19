package com.kusithms.dongastd.domain.content.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusithms.dongastd.domain.content.entity.Content;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
