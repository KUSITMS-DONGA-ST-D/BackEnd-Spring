package com.kusithms.dongastd.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kusithms.dongastd.domain.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {


}
