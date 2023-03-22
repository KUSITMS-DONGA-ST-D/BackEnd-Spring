package com.kusithms.dongastd.domain.comment.respository;

import com.kusithms.dongastd.domain.comment.entity.Comment;
import com.kusithms.dongastd.domain.content.entity.Content;
import com.kusithms.dongastd.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByContentAndCreatedDateBetween(Content content,LocalDateTime start, LocalDateTime end);

    List<Comment> findAllByUserAndContentAndCreatedDateBetween(User user, Content content, LocalDateTime start, LocalDateTime end);
}
