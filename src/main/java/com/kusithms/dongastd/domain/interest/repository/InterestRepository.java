package com.kusithms.dongastd.domain.interest.repository;

import com.kusithms.dongastd.domain.content.entity.Content;
import com.kusithms.dongastd.domain.interest.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findAllByContent(Content content);
}
