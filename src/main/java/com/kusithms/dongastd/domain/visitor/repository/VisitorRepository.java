package com.kusithms.dongastd.domain.visitor.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kusithms.dongastd.domain.visitor.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

	Page<Visitor> findByCreatedDateBefore(LocalDateTime localDateTime, Pageable pageable);

	List<Visitor> findByCreatedDateBetween(LocalDateTime start, LocalDateTime end, Sort sort);
}
