package com.kusithms.dongastd.domain.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phase2 implements Comparable<Phase2>{
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH-mm")
	private LocalDateTime created_date;
	private int conversion_rate;
	private int accumulate_users;
	private int new_users;

	@Builder
	public Phase2(LocalDateTime created_date, int conversion_rate, int accumulate_users, int new_users) {
		this.created_date = created_date;
		this.conversion_rate = conversion_rate;
		this.accumulate_users = accumulate_users;
		this.new_users = new_users;
	}

	@Override
	public String toString() {
		return "Phase2{" +
			"createdDate=" + created_date +
			", conversionRate=" + conversion_rate +
			", accumulateUser=" + accumulate_users +
			", newUser=" + new_users +
			'}';
	}

	@Override
	public int compareTo(Phase2 o) {
		return created_date.compareTo(o.created_date);
	}
}
