package com.kusithms.dongastd.domain.controller.dto;

import java.time.Duration;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kusithms.dongastd.domain.pageview.entity.PageView;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phase3 implements Comparable<Phase3> {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH-mm")
	private LocalDateTime created_date;
	private int view_number;
	private int session_time;


	@Builder
	public Phase3(LocalDateTime created_date, int view_number, int session_time) {
		this.created_date = created_date;
		this.view_number = view_number;
		this.session_time = session_time;
	}

	public static Phase3 of(PageView pageView) {
		return Phase3.builder()
			.created_date(pageView.getCreatedDate())
			.view_number(pageView.getViewCount())
			.session_time((int)pageView.getDuration().getSeconds())
			.build();
	}

	public static Phase3 of(LocalDateTime localDateTime, int viewNumber, Duration duration){
		return Phase3.builder()
			.created_date(localDateTime)
			.view_number(viewNumber)
			.session_time((int)duration.getSeconds())
			.build();
	}

	@Override
	public String toString() {
		return "Phase3{" +
			"createDate=" + created_date +
			", viewNumber=" + view_number +
			", sessionTime=" + session_time +
			'}';
	}

	@Override
	public int compareTo(Phase3 o) {
		return created_date.compareTo(o.getCreated_date());
	}
}
