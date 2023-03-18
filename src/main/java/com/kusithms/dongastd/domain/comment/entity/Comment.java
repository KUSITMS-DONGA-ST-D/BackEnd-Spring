package com.kusithms.dongastd.domain.comment.entity;

import com.kusithms.dongastd.common.domain.BaseEntity;
import com.kusithms.dongastd.domain.content.entity.Content;
import com.kusithms.dongastd.domain.users.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

	@Id@GeneratedValue
	@Column(name = "comment_id")
	private Long id;

	@Column(name = "comment")
	private String comment;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "content_id")
	private Content content;


	@Builder
	public Comment(Long id, String comment, User user, Content content) {
		this.id = id;
		this.comment = comment;
		this.user = user;
		this.content = content;
	}
}
