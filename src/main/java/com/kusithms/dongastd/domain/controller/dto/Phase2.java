package com.kusithms.dongastd.domain.controller.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phase2 {

	private LocalDate createdDate;
	private int conversionRate;
	private int accumulateUser;
	private int newUser;

	@Builder
	public Phase2(LocalDate createdDate, int conversionRate, int accumulateUser, int newUser) {
		this.createdDate = createdDate;
		this.conversionRate = conversionRate;
		this.accumulateUser = accumulateUser;
		this.newUser = newUser;
	}

	@Override
	public String toString() {
		return "Phase2{" +
			"createdDate=" + createdDate +
			", conversionRate=" + conversionRate +
			", accumulateUser=" + accumulateUser +
			", newUser=" + newUser +
			'}';
	}
}
