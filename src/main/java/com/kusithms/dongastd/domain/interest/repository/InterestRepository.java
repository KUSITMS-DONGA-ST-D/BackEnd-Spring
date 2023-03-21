package com.kusithms.dongastd.domain.interest.repository;

import com.kusithms.dongastd.domain.content.entity.Content;
import com.kusithms.dongastd.domain.interest.entity.Interest;
import com.kusithms.dongastd.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findAllByContent(Content content);
    Optional<Interest> findByUserAndContentAndCreatedDateBetween(User user, Content content, LocalDateTime start, LocalDateTime end);
}
