package com.kusithms.dongastd.domain.pageview.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kusithms.dongastd.domain.pageview.entity.PageView;

public interface PageViewRepository extends JpaRepository<PageView, Long> {

	Page<PageView> findByCreatedDateBefore(LocalDateTime localDateTime,Pageable pageable);

	List<PageView> findByCreatedDateBetween(LocalDateTime start, LocalDateTime end, Sort sort);


}
