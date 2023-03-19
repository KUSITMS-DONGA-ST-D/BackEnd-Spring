package com.kusithms.dongastd.domain.controller.dto;

import java.time.LocalDate;

import com.kusithms.dongastd.domain.visitor.entity.Visitor;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phase1 {

	private LocalDate localDate;
	private int newVisitor;
	private int againVisitor;
	private int totalVisitor;

	@Builder
	public Phase1(LocalDate localDate, int newVisitor, int againVisitor, int totalVisitor) {
		this.localDate = localDate;
		this.newVisitor = newVisitor;
		this.againVisitor = againVisitor;
		this.totalVisitor = totalVisitor;
	}

	public static Phase1 of(Visitor visitor, LocalDate localDate){
		return Phase1.builder()
			.localDate(localDate)
			.newVisitor(visitor.getNewVisitor())
			.againVisitor(visitor.getAgainVisitor())
			.totalVisitor(visitor.getNewVisitor() + visitor.getAgainVisitor())
			.build();
	}

	public static Phase1 of(Visitor visitor){
		return Phase1.builder()
			.localDate(visitor.getCreatedDate().toLocalDate())
			.newVisitor(visitor.getNewVisitor())
			.againVisitor(visitor.getAgainVisitor())
			.totalVisitor(visitor.getNewVisitor() + visitor.getAgainVisitor())
			.build();
	}

	@Override
	public String toString() {
		return "Phase1{" +
			"localDate=" + localDate +
			", newVisitor=" + newVisitor +
			", againVisitor=" + againVisitor +
			", totalVisitor=" + totalVisitor +
			'}';
	}
}
