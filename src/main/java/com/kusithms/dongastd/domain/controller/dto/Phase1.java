package com.kusithms.dongastd.domain.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kusithms.dongastd.domain.visitor.entity.Visitor;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phase1 implements Comparable<Visitor>{


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH-mm", timezone = "Asia/Seoul")
	private LocalDateTime localDateTime;
	private int newVisitor;
	private int againVisitor;
	private int totalVisitor;

	@Builder
	public Phase1(LocalDateTime localDateTime, int newVisitor, int againVisitor, int totalVisitor) {
		this.localDateTime = localDateTime;
		this.newVisitor = newVisitor;
		this.againVisitor = againVisitor;
		this.totalVisitor = totalVisitor;
	}

	public static Phase1 of(Visitor visitor, LocalDateTime localDateTime){
		return Phase1.builder()
			.localDateTime(localDateTime)
			.newVisitor(visitor.getNewVisitor())
			.againVisitor(visitor.getAgainVisitor())
			.totalVisitor(visitor.getNewVisitor() + visitor.getAgainVisitor())
			.build();
	}

	public static Phase1 of(Visitor visitor){
		return Phase1.builder()
			.localDateTime(visitor.getCreatedDate())
			.newVisitor(visitor.getNewVisitor())
			.againVisitor(visitor.getAgainVisitor())
			.totalVisitor(visitor.getNewVisitor() + visitor.getAgainVisitor())
			.build();
	}

	@Override
	public String toString() {
		return "Phase1{" +
			"localDateTime=" + localDateTime +
			", newVisitor=" + newVisitor +
			", againVisitor=" + againVisitor +
			", totalVisitor=" + totalVisitor +
			'}';
	}

	@Override
	public int compareTo(Visitor o) {
		return localDateTime.compareTo(o.getCreatedDate());
	}
}
