package com.kusithms.dongastd.domain.memo.entity;

import com.kusithms.dongastd.common.domain.BaseEntity;
import com.kusithms.dongastd.domain.users.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "MEMO")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Memo extends BaseEntity {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memo_id")
	private Long id;

	@Column(name = "memo_content")
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Memo(Long id, String content) {
		this.id = id;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Memo{" +
			"id=" + id +
			", content='" + content + '\'' +
			'}';
	}
}
