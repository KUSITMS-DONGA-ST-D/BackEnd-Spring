package com.kusithms.dongastd.domain.contentdata.repository;

import com.kusithms.dongastd.domain.contentdata.entity.ContentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ContentDataRepository extends JpaRepository<ContentData, Long> {
    List<ContentData> findByCreatedDateBetween(LocalDate yesterday, LocalDate today);
}
