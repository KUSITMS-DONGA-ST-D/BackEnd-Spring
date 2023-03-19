package com.kusithms.dongastd.domain.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kusithms.dongastd.domain.controller.dto.Phase1;
import com.kusithms.dongastd.domain.visitor.entity.Visitor;
import com.kusithms.dongastd.domain.visitor.repository.VisitorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

	private final VisitorRepository visitorRepository;

	@GetMapping("/totalgrowthbarchart-phase1")
	public List<Phase1> totalGrowthBarChartPhase1(@RequestParam("day") int day) {
		List<Phase1> list = new ArrayList<>();

		List<Visitor> visitors =
			visitorRepository.findAll(PageRequest.of(0,day, Sort.by("id").descending())).getContent();

		for (Visitor visitor : visitors) {
			list.add(Phase1.of(visitor));
		}
		return list;
	}


}
