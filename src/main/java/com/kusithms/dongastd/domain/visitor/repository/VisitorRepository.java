package com.kusithms.dongastd.domain.visitor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kusithms.dongastd.domain.visitor.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

	Page<Visitor> findAll(Pageable pageable);
}
