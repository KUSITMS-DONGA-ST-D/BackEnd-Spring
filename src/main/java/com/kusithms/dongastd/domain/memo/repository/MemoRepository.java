package com.kusithms.dongastd.domain.memo.repository;

import com.kusithms.dongastd.domain.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
}
