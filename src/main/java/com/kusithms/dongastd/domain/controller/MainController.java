package com.kusithms.dongastd.domain.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kusithms.dongastd.domain.controller.dto.Phase1;
import com.kusithms.dongastd.domain.controller.dto.Phase2;
import com.kusithms.dongastd.domain.controller.dto.Phase3;
import com.kusithms.dongastd.domain.pageview.entity.PageView;
import com.kusithms.dongastd.domain.pageview.repository.PageViewRepository;
import com.kusithms.dongastd.domain.visitor.entity.Visitor;
import com.kusithms.dongastd.domain.visitor.repository.VisitorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

	private final VisitorRepository visitorRepository;
	private final PageViewRepository pageViewRepository;

	@GetMapping("/totalgrowthbarchart-phase1")
	public List<Phase1> totalGrowthBarChartPhase1(@RequestParam("day") int day) {
		List<Phase1> list = new ArrayList<>();

		if(day == 1){
			List<Visitor> visitorList = visitorRepository
				.findByCreatedDateBefore(
					LocalDateTime.of(2023, 3, 28, 15, 00),
					PageRequest.of(0, 24, Sort.by("id").descending()))
				.getContent();

			for (Visitor visitor : visitorList) {
				list.add(Phase1.of(visitor));
			}
		} else{
			LocalDateTime endTime = LocalDateTime.of(2023, 3, 28, 15, 00);
			List<Visitor> visitorList = visitorRepository
				.findByCreatedDateBetween(endTime.minusDays(day), endTime, Sort.by("id").descending());
			Map<LocalDate, List<Visitor>> collect = visitorList.stream()
				.collect(Collectors.groupingBy(visitor -> visitor.getCreatedDate().toLocalDate()));

			for (LocalDate localDate : collect.keySet()) {
				int newVisitor = 0;
				int againVisitor = 0;

				for (Visitor visitor : collect.get(localDate)) {
					newVisitor+=visitor.getNewVisitor();
					againVisitor+=visitor.getAgainVisitor();
				}

				list.add(Phase1.builder()
					.newVisitor(newVisitor)
					.againVisitor(againVisitor)
					.totalVisitor(newVisitor+againVisitor)
					.createDate(localDate.atStartOfDay())
					.build());
			}
		}

		List<Phase1> collect = list.stream()
			.sorted(Comparator.comparing(Phase1::getCreateDate).reversed())
			.collect(Collectors.toList());
		log.info(collect.toString());
		return collect;
	}

	@GetMapping("/totalgrowthbarchart-phase2")
	public List<Phase2> totalGrowthBarChartPhase2(@RequestParam("day") int day) {
		List<Phase2> list = new ArrayList<>();

		if(day == 1){


		} else{

		}
		return list;
	}

	@GetMapping("/totalgrowthbarchart-phase3")
	public List<Phase3> totalGrowthBarChartPhase3(@RequestParam("day") int day) {
		List<Phase3> list = new ArrayList<>();

		if(day == 1){
			List<PageView> pageViewList = pageViewRepository
				.findByCreatedDateBefore(
					LocalDateTime.of(2023, 3, 28, 15, 00),
					PageRequest.of(0, 24, Sort.by("id").descending()))
				.getContent();

			for (PageView pageView : pageViewList) {
				list.add(Phase3.of(pageView));
			}
		} else{
			LocalDateTime endTime = LocalDateTime.of(2023, 3, 28, 15, 00);
			List<PageView> pageViewList = pageViewRepository
				.findByCreatedDateBetween(endTime.minusDays(day), endTime, Sort.by("id").descending());
			Map<LocalDate, List<PageView>> collect = pageViewList.stream()
				.collect(Collectors.groupingBy(pageView -> pageView.getCreatedDate().toLocalDate()));

			for (LocalDate localDate : collect.keySet()) {
				int viewCount=0;
				Duration duration = Duration.ofSeconds(0L);

				for(PageView pageView :collect.get(localDate)){
					viewCount+=pageView.getViewCount();
					duration = duration.plus(pageView.getDuration());
				}
				duration.dividedBy(collect.get(localDate).size());

				list.add(Phase3.of(localDate.atStartOfDay(), viewCount, duration));
			}
		}

		List<Phase3> collect = list.stream()
			.sorted(Comparator.comparing(Phase3::getCreateDate).reversed())
			.collect(Collectors.toList());
		log.info(collect.toString());


		return collect;
	}

}
