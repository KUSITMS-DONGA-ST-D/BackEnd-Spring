package com.kusithms.dongastd.domain.livebroadcast.entity;

import java.time.LocalDateTime;

import com.kusithms.dongastd.common.domain.BaseEntity;
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
public class LiveBroadcast extends BaseEntity {

	@Id@GeneratedValue
	@Column(name = "live_id")
	private Long id;

	@Column(name = "live_title")
	private String title;

	@Column(name = "live_url")
	private String url;

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public LiveBroadcast(Long id, String title, String url, LocalDateTime startTime, LocalDateTime endTime) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "LiveBroadcast{" +
			"id=" + id +
			", title='" + title + '\'' +
			", url='" + url + '\'' +
			", startTime=" + startTime +
			", endTime=" + endTime +
			'}';
	}
}
