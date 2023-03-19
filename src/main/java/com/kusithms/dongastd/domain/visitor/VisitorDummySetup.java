package com.kusithms.dongastd.domain.visitor;

import org.springframework.stereotype.Component;

import com.kusithms.dongastd.domain.visitor.repository.VisitorRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VisitorDummySetup {

	private final VisitorRepository visitorRepository;

	@PostConstruct
	public void init() {
		// for(int i=50; i>=0; i--){
		// 	int newVisitor = (int)(Math.random() * 30) +1;
		// 	int againVisitor = (int)(Math.random() * 50) +1;
		//
		// 	Visitor save = visitorRepository.save(Visitor.builder()
		// 		.newVisitor(newVisitor)
		// 		.againVisitor(againVisitor)
		// 			.localDateTime(LocalDateTime.now().minusDays(i))
		// 		.build());
		// }
	}
}
