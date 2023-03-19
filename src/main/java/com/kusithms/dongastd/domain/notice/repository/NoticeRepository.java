package com.kusithms.dongastd.domain.notice.repository;

import com.kusithms.dongastd.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
