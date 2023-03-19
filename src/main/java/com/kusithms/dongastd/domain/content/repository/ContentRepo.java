package com.kusithms.dongastd.domain.content.repository;

import com.kusithms.dongastd.domain.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepo extends JpaRepository<Content, Long> {
}
