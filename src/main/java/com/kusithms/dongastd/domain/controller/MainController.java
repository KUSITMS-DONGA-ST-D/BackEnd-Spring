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
import com.kusithms.dongastd.domain.users.entity.User;
import com.kusithms.dongastd.domain.users.repository.UserRepository;
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
	private final UserRepository userRepository;

	@GetMapping("/totalgrowthbarchart-phase1")
	public List<Phase1> totalGrowthBarChartPhase1(@RequestParam("day") int day) {
		List<Phase1> list = new ArrayList<>();

		if(day == 1){
			List<Visitor> visitorList = visitorRepository
				.findByCreatedDateBefore(
					LocalDateTime.of(2023, 3, 28, 15, 59),
					PageRequest.of(0, 24, Sort.by("id").descending()))
				.getContent();

			for (Visitor visitor : visitorList) {
				list.add(Phase1.of(visitor));
			}
		} else{
			LocalDateTime endTime = LocalDateTime.of(2023, 3, 28, 15, 59);
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
					.new_visitors(newVisitor)
					.again_visitors(againVisitor)
					.total_visitors(newVisitor+againVisitor)
					.created_date(localDate.atStartOfDay())
					.build());
			}
		}

		List<Phase1> collect = list.stream()
			.sorted(Comparator.comparing(Phase1::getCreated_date).reversed())
			.collect(Collectors.toList());
		log.info(collect.toString());
		return collect;
	}

	@GetMapping("/totalgrowthbarchart-phase2")
	public List<Phase2> totalGrowthBarChartPhase2(@RequestParam("day") int day) {
		List<Phase2> list = new ArrayList<>();

		if(day == 1){
			Map<LocalDateTime, List<User>> newUserListByHour = userRepository.findByCreatedDateBetween(
					LocalDateTime.of(2023,3,27,15,59),
					LocalDateTime.of(2023, 3, 28, 15, 59))
				.stream().collect(Collectors.groupingBy(User::getCreatedDate));

			List<Visitor> visitorList = visitorRepository.findByCreatedDateBefore(
					LocalDateTime.of(2023, 3, 28, 15, 59),
					PageRequest.of(0, 24, Sort.by("id").descending()))
				.getContent();

			for (LocalDateTime localDateTime : newUserListByHour.keySet()) {
				int newUser = newUserListByHour.get(localDateTime).size();
				int visitorCount = 0;
				for (Visitor visitor : visitorList) {
					if(visitor.getCreatedDate().isEqual(localDateTime)){
						visitorCount+=visitor.getNewVisitor();
					}
				}
				list.add(Phase2.builder()
					.new_users(newUser)
					.accumulate_users(userRepository.findByCreatedDateBefore(localDateTime.plusHours(1)).size()+10296)
					.conversion_rate((int)((double)newUser/(double)visitorCount*100))
					.created_date(localDateTime)
					.build());
			}
		} else{
			LocalDateTime endTime = LocalDateTime.of(2023, 3, 28, 15, 59);

			Map<LocalDate, List<User>> newUserListByHour = userRepository.findByCreatedDateBetween(
					endTime.minusDays(day),endTime)
				.stream().collect(Collectors.groupingBy(user -> user.getCreatedDate().toLocalDate()));

			Map<LocalDate, List<Visitor>> visitorList = visitorRepository
				.findByCreatedDateBetween(endTime.minusDays(day), endTime, Sort.by("id").descending())
				.stream()
				.collect(Collectors.groupingBy(visitor -> visitor.getCreatedDate().toLocalDate()));

			for (LocalDate localDate : newUserListByHour.keySet()) {
				int newUser = newUserListByHour.get(localDate).size();
				int visitorCount = 0;
				for (Visitor visitor : visitorList.get(localDate)) {
					visitorCount+=visitor.getNewVisitor();
				}
				list.add(Phase2.builder()
					.new_users(newUser)
					.accumulate_users(userRepository.findByCreatedDateBefore(localDate.atStartOfDay().plusDays(1)).size()+10296)
					.conversion_rate((int)((double)newUser/(double)visitorCount*100))
					.created_date(localDate.atStartOfDay())
					.build());
			}
		}

		List<Phase2> collect = list.stream()
			.sorted(Comparator.comparing(Phase2::getCreated_date).reversed())
			.collect(Collectors.toList());
		return collect;
	}

	@GetMapping("/totalgrowthbarchart-phase3")
	public List<Phase3> totalGrowthBarChartPhase3(@RequestParam("day") int day) {
		List<Phase3> list = new ArrayList<>();

		if(day == 1){
			List<PageView> pageViewList = pageViewRepository
				.findByCreatedDateBefore(
					LocalDateTime.of(2023, 3, 28, 15, 59),
					PageRequest.of(0, 24, Sort.by("id").descending()))
				.getContent();

			for (PageView pageView : pageViewList) {
				list.add(Phase3.of(pageView));
			}
		} else{
			LocalDateTime endTime = LocalDateTime.of(2023, 3, 28, 15, 59);
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
				duration = duration.dividedBy(collect.get(localDate).size());

				list.add(Phase3.of(localDate.atStartOfDay(), viewCount, duration));
			}
		}

		List<Phase3> collect = list.stream()
			.sorted(Comparator.comparing(Phase3::getCreated_date).reversed())
			.collect(Collectors.toList());
		log.info(collect.toString());


		return collect;
	}

}
