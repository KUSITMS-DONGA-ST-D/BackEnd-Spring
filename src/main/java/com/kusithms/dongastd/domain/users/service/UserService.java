package com.kusithms.dongastd.domain.users.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kusithms.dongastd.domain.users.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;

	@PostConstruct
	public void init() {
		// User user = User.builder()
		// 	.age(30)
		// 	.email("test@test.com")
		// 	.username("test")
		// 	.gender(Gender.MALE)
		// 	.password("test")
		// 	.role(UserRole.DOCTOR)
		// 	.build();
		//
		// log.info("user: {}", user);
		// userRepository.save(user);

	}
}
