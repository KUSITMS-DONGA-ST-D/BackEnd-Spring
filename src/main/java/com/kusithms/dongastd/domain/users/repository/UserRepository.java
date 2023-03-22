package com.kusithms.dongastd.domain.users.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.kusithms.dongastd.domain.users.entity.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kusithms.dongastd.domain.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByCreatedDateBefore(LocalDateTime localDateTime, Pageable pageable);

    List<User> findByCreatedDateBefore(LocalDateTime localDateTime);

    List<User> findByCreatedDateBetween(LocalDateTime start, LocalDateTime end);

    List<User> findAllByAgeIsBetween(int startAge, int endAge);

    List<User> findAllByAgeIsBetweenAndGender(int startAge, int endAge, Gender gender);
    List<User> findAllByAgeIsBetweenAndCategory(int startAge, int endAge, String category);

    List<User> findAllByGenderAndCategory(Gender gender, String category);
    List<User> findAllByGender(Gender gender);

    List<User> findAllByAgeIsBetweenAndGenderAndCategory(int startAge, int endAge, Gender gender, String category);

}