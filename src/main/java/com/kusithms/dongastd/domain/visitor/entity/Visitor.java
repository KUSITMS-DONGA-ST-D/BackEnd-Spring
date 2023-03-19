package com.kusithms.dongastd.domain.visitor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Visitor {

	@Id@GeneratedValue
	@Column(name = "visitor_id")
	private Long id;

	@Column(name = "new_visitor")
	private int newVisitor;
	@Column(name = "again_visitor")
	private int againVisitor;

	@Builder
	public Visitor(Long id, int newVisitor, int againVisitor) {
		this.id = id;
		this.newVisitor = newVisitor;
		this.againVisitor = againVisitor;
	}

	@Override
	public String toString() {
		return "Visitor{" +
			"id=" + id +
			", newVisitor=" + newVisitor +
			", againVisitor=" + againVisitor +
			'}';
	}
}
