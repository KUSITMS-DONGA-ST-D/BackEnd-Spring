package com.kusithms.dongastd.domain.content.entity;

import com.kusithms.dongastd.common.domain.BaseEntity;
import com.kusithms.dongastd.domain.users.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jdk.jfr.ContentType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "CONTENTS")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Content extends BaseEntity {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "content_id")
	private Long id;

	@Column(name = "content_title")
	private String title;

	@Column(name = "content_explain")
	private String vodExplain;

	@Column(name = "content_category")
	private String category;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Content(Long id, String title, String vodExplain, String category) {
		this.id = id;
		this.title = title;
		this.vodExplain = vodExplain;
		this.category = category;
	}

	public void addUser(User user) {
		this.user = user;
	}
}
