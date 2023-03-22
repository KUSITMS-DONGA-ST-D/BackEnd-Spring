package com.kusithms.dongastd.domain.users.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kusithms.dongastd.domain.users.entity.Gender;
import com.kusithms.dongastd.domain.users.entity.User;
import com.kusithms.dongastd.domain.users.entity.UserRole;
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
		for(int i = 24; i>=0; i--){
			for(int j=0; j<(int)(Math.random()*5)+1; j++){
				// User user = User.builder()
				// 	.age((int)(Math.random() * 50) + 20)
				// 	.username("test")
				// 	.password("test")
				// 	.email("test@test.com")
				// 	.role(UserRole.DOCTOR)
				// 	.gender(Gender.FEMALE)
				// 	.category(randomCategory())
				// 	.build();
				//
				// user.setCreatedDate(LocalDateTime.of(2023, 03, 28, 15, 00).minusHours(i));
				//
				// userRepository.save(user);
			}
		}
	}

	private String randomCategory(){
		int random = (int)(Math.random()*12)+1;
		if(random%4 == 0) {
			return "내과학";
		}else if(random%4 == 1){
			return "외과학";
		}else if(random%4 == 2) {
			return "임상계열";
		}else{
			return "기타";
		}
	}
}
