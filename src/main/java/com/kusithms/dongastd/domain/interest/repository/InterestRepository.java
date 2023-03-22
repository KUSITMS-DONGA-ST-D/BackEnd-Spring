package com.kusithms.dongastd.domain.interest.repository;

import com.kusithms.dongastd.domain.comment.entity.Comment;
import com.kusithms.dongastd.domain.content.entity.Content;
import com.kusithms.dongastd.domain.interest.entity.Interest;
import com.kusithms.dongastd.domain.users.entity.Gender;
import com.kusithms.dongastd.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findAllByContentAndCreatedDateBetween(Content content, LocalDateTime start, LocalDateTime end);

    List<Interest> findAllByUser_CategoryAndContentAndCreatedDateBetween(String category, Content content, LocalDateTime start, LocalDateTime end);
    List<Interest> findAllByUser_GenderAndContentAndCreatedDateBetween(Gender gender, Content content, LocalDateTime start, LocalDateTime end);
    List<Interest> findAllByUser_AgeIsBetweenAndContentAndCreatedDateBetween(int startAge, int endAge, Content content, LocalDateTime start, LocalDateTime end);
    List<Interest> findAllByUser_AgeIsBetweenAndUser_GenderAndContentAndCreatedDateBetween(int startAge, int endAge, Gender gender, Content content, LocalDateTime start, LocalDateTime end);
    List<Interest> findAllByUser_AgeIsBetweenAndUser_CategoryAndContentAndCreatedDateBetween(int startAge, int endAge, String category, Content content, LocalDateTime start, LocalDateTime end);
    List<Interest> findAllByUser_GenderAndUser_CategoryAndContentAndCreatedDateBetween(Gender gender, String category, Content content, LocalDateTime start, LocalDateTime end);
    List<Interest> findAllByUser_AgeIsBetweenAndUser_GenderAndUser_CategoryAndContentAndCreatedDateBetween(int startAge, int endAge, Gender gender, String category, Content content, LocalDateTime start, LocalDateTime end);
    Optional<Interest> findByUserAndContentAndCreatedDateBetween(User user, Content content, LocalDateTime start, LocalDateTime end);
}
