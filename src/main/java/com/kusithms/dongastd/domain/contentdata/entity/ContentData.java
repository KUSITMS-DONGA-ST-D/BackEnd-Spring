package com.kusithms.dongastd.domain.contentdata.entity;

import java.time.Duration;

import com.kusithms.dongastd.common.domain.BaseEntity;
import com.kusithms.dongastd.domain.content.entity.Content;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentData extends BaseEntity {

	@Id@GeneratedValue
	@Column(name = "content_data_id")
	private Long id;

	private String url;

	@Column(name = "average_view_time")
	private Duration duration;

	private int hitCount;

	@Builder
	public ContentData(Long id, String url, Duration duration, int hitCount) {
		this.id = id;
		this.url = url;
		this.duration = duration;
		this.hitCount = hitCount;
	}

	@ManyToOne
	@JoinColumn(name = "content_id")
	private Content content;
}
