package com.kusithms.dongastd.domain.pageview.entity;

import java.time.Duration;

import com.kusithms.dongastd.common.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PageView extends BaseEntity {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "page_view_id")
	private Long id;

	private int viewCount;

	private Duration duration;

	@Builder
	public PageView(Long id, int viewCount, Duration duration) {
		this.id = id;
		this.viewCount = viewCount;
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "PageView{" +
			"id=" + id +
			", viewCount=" + viewCount +
			", duration=" + duration +
			'}';
	}
}
