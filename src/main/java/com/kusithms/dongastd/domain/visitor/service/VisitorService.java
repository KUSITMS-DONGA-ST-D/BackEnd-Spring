package com.kusithms.dongastd.domain.visitor.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.kusithms.dongastd.domain.visitor.entity.Visitor;
import com.kusithms.dongastd.domain.visitor.repository.VisitorRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VisitorService {

	private final VisitorRepository visitorRepository;

	@PostConstruct
	public void init() {
		// for(int i=24; i>=0; i--){
		// 	int newVisitor = (int)(Math.random() * 4) +1;
		// 	int againVisitor = (int)(Math.random() * 4) +1;
		//
		// 	Visitor save = visitorRepository.save(Visitor.builder()
		// 		.newVisitor(newVisitor)
		// 		.againVisitor(againVisitor)
		// 			.localDateTime(LocalDateTime.of(2023,3,28,15,00).minusHours(i))
		// 		.build());
		// }
	}
}
