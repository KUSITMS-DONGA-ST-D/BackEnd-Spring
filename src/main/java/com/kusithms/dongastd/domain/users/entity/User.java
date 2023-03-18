package com.kusithms.dongastd.domain.users.entity;

import java.time.LocalDateTime;

import com.kusithms.dongastd.common.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

	@Id@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	private String email;

	private String username;
	private String password;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	// delete 상태 일때는 lastModifiedDate를 통해 삭제 시간을 알 수 있음
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	private int age;

	private LocalDateTime lastLoginDate;

	@Builder
	public User(Long id, String email, String username, String password, UserRole role, Gender gender, int age) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.gender = gender;
		this.status = UserStatus.NORMAL;
		this.age = age;
		this.lastLoginDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", email='" + email + '\'' +
			", username='" + username + '\'' +
			", password='" + password + '\'' +
			", role=" + role +
			", sex=" + gender +
			", status=" + status +
			", age=" + age +
			", lastLoginDate=" + lastLoginDate +
			'}';
	}
}
