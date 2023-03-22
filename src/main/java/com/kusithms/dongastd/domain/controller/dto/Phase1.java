package com.kusithms.dongastd.domain.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kusithms.dongastd.domain.visitor.entity.Visitor;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phase1 implements Comparable<Visitor>{


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH-mm")
	private LocalDateTime created_date;
	private int new_visitors;
	private int again_visitors;
	private int total_visitors;

	@Builder
	public Phase1(LocalDateTime created_date, int new_visitors, int again_visitors, int total_visitors) {
		this.created_date = created_date;
		this.new_visitors = new_visitors;
		this.again_visitors = again_visitors;
		this.total_visitors = total_visitors;
	}

	public static Phase1 of(Visitor visitor, LocalDateTime localDateTime){
		return Phase1.builder()
			.created_date(localDateTime)
			.new_visitors(visitor.getNewVisitor())
			.again_visitors(visitor.getAgainVisitor())
			.total_visitors(visitor.getNewVisitor() + visitor.getAgainVisitor())
			.build();
	}

	public static Phase1 of(Visitor visitor){
		return Phase1.builder()
			.created_date(visitor.getCreatedDate())
			.new_visitors(visitor.getNewVisitor())
			.again_visitors(visitor.getAgainVisitor())
			.total_visitors(visitor.getNewVisitor() + visitor.getAgainVisitor())
			.build();
	}

	@Override
	public String toString() {
		return "Phase1{" +
			"createDate=" + created_date +
			", newVisitor=" + new_visitors +
			", againVisitor=" + again_visitors +
			", totalVisitor=" + total_visitors +
			'}';
	}

	@Override
	public int compareTo(Visitor o) {
		return created_date.compareTo(o.getCreatedDate());
	}
}
