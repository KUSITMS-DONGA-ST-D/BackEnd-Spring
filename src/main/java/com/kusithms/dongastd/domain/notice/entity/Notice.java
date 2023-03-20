package com.kusithms.dongastd.domain.notice.entity;

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
@Table(name = "NOTICE")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Notice extends BaseEntity {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notice_id")
	private Long id;

	@Column(name = "notice_title")
	private String title;

	@Column(name = "notice_content")
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public void addUser(User user) {
		this.user = user;
	}

	@Builder
	public Notice(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Notice{" +
			"id=" + id +
			", title='" + title + '\'' +
			", content='" + content + '\'' +
			'}';
	}
}
