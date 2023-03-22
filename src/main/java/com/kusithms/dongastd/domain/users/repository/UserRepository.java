package com.kusithms.dongastd.domain.users.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kusithms.dongastd.domain.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByCreatedDateBefore(LocalDateTime localDateTime, Pageable pageable);

    List<User> findByCreatedDateBefore(LocalDateTime localDateTime);

    List<User> findByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
}