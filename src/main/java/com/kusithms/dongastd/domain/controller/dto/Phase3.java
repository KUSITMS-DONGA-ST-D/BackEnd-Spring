package com.kusithms.dongastd.domain.controller.dto;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kusithms.dongastd.domain.pageview.entity.PageView;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phase3 implements Comparable<Phase3> {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH-mm", timezone = "Asia/Seoul")
	private LocalDateTime localDateTime;
	private int viewNumber;
	private int sessionTime;


	@Builder
	public Phase3(LocalDateTime localDateTime, int viewNumber, int sessionTime) {
		this.localDateTime = localDateTime;
		this.viewNumber = viewNumber;
		this.sessionTime = sessionTime;
	}

	public static Phase3 of(PageView pageView) {
		return Phase3.builder()
			.localDateTime(pageView.getCreatedDate())
			.viewNumber(pageView.getViewCount())
			.sessionTime((int)pageView.getDuration().getSeconds())
			.build();
	}

	public static Phase3 of(LocalDateTime localDateTime, int viewNumber, Duration duration){
		return Phase3.builder()
			.localDateTime(localDateTime)
			.viewNumber(viewNumber)
			.sessionTime((int)duration.getSeconds())
			.build();
	}

	@Override
	public String toString() {
		return "Phase3{" +
			"localDateTime=" + localDateTime +
			", viewNumber=" + viewNumber +
			", sessionTime=" + sessionTime +
			'}';
	}

	@Override
	public int compareTo(Phase3 o) {
		return localDateTime.compareTo(o.getLocalDateTime());
	}
}
