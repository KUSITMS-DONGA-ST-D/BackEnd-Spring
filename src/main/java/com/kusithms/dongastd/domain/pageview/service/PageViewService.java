package com.kusithms.dongastd.domain.pageview.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kusithms.dongastd.domain.pageview.entity.PageView;
import com.kusithms.dongastd.domain.pageview.repository.PageViewRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PageViewService {

	private final PageViewRepository pageViewRepository;

	@PostConstruct
	public void init() {
		// for(int i = 3; i>0 ; i--){
		// 	PageView pageView = PageView.builder()
		// 		.viewCount((int)(Math.random()* 100) + 1)
		// 		.duration(Duration.ofSeconds((long)(Math.random()*100) + 1))
		// 		.localDateTime(LocalDateTime.of(2023,3,28,15,00).minusDays(i))
		// 		.build();
		//
		// 	pageViewRepository.save(pageView);
		// }
		//
		//
		// for(int i = 24; i>=0 ; i--){
		// 	PageView pageView = PageView.builder()
		// 		.viewCount((int)(Math.random()* 8) + 1)
		// 		.duration(Duration.ofSeconds((long)(Math.random()*100) + 1))
		// 		.localDateTime(LocalDateTime.of(2023,3,28,15,00).minusHours(i))
		// 		.build();
		//
		// 	pageViewRepository.save(pageView);
		// }
	}



}
