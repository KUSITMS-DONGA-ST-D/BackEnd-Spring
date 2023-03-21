package com.kusithms.dongastd.domain.visitor.entity;

import java.time.LocalDateTime;

import com.kusithms.dongastd.common.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Visitor extends BaseEntity {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "visitor_id")
	private Long id;

	@Column(name = "new_visitor")
	private int newVisitor;
	@Column(name = "again_visitor")
	private int againVisitor;

	@Builder
	public Visitor(Long id, int newVisitor, int againVisitor, LocalDateTime localDateTime) {
		this.id = id;
		this.newVisitor = newVisitor;
		this.againVisitor = againVisitor;
		setCreatedDate(localDateTime);
	}

	public static Visitor emptyVisitor() {
		return Visitor.builder()
			.newVisitor(0)
			.againVisitor(0)
			.build();
	}

	@Override
	public String toString() {
		return "Visitor{" +
			"id=" + id +
			", newVisitor=" + newVisitor +
			", againVisitor=" + againVisitor +
			"createdDate=" + getCreatedDate() +
			'}';
	}
}
