package com.kusithms.dongastd.domain.users.repository;

import com.kusithms.dongastd.domain.users.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kusithms.dongastd.domain.users.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByAge(int age);
    List<User> findAllByAgeAndGender(int age, Gender gender);
    List<User> findAllByGenderAndCategory(Gender gender, String category);
    List<User> findAllByAgeIsBetweenAndGenderAndCategory(int startAge, int endAge, Gender gender, String category);
}
