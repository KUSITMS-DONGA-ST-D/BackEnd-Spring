package com.kusithms.dongastd.domain.pageview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusithms.dongastd.domain.pageview.entity.PageView;

public interface PageViewRepository extends JpaRepository<PageView, Long> {
}
