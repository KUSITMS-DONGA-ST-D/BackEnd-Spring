package com.kusithms.dongastd.domain.comment.respository;

import com.kusithms.dongastd.domain.comment.entity.Comment;
import com.kusithms.dongastd.domain.content.entity.Content;
import com.kusithms.dongastd.domain.users.entity.Gender;
import com.kusithms.dongastd.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByContentAndCreatedDateBetween(Content content,LocalDateTime start, LocalDateTime end);

    List<Comment> findAllByUserAndContentAndCreatedDateBetween(User user, Content content, LocalDateTime start, LocalDateTime end);
    List<Comment> findAllByUser_GenderAndContentAndCreatedDateBetween(Gender gender, Content content, LocalDateTime start, LocalDateTime end);
    List<Comment> findAllByUser_CategoryAndContentAndCreatedDateBetween(String category, Content content, LocalDateTime start, LocalDateTime end);

    List<Comment> findAllByUser_AgeIsBetweenAndContentAndCreatedDateBetween(int startAge, int endAge, Content content, LocalDateTime start, LocalDateTime end);
    List<Comment> findAllByUser_AgeIsBetweenAndUser_GenderAndContentAndCreatedDateBetween(int startAge, int endAge, Gender gender, Content content, LocalDateTime start, LocalDateTime end);
    List<Comment> findAllByUser_AgeIsBetweenAndUser_CategoryAndContentAndCreatedDateBetween(int startAge, int endAge, String category, Content content, LocalDateTime start, LocalDateTime end);
    List<Comment> findAllByUser_AgeIsBetweenAndUser_GenderAndUser_CategoryAndContentAndCreatedDateBetween(int startAge, int endAge, Gender gender, String category, Content content, LocalDateTime start, LocalDateTime end);
    List<Comment> findAllByUser_GenderAndUser_CategoryAndContentAndCreatedDateBetween(Gender gender, String category, Content content, LocalDateTime start, LocalDateTime end);
}
