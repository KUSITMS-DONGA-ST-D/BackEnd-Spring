package com.kusithms.dongastd.domain.pageview.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		// PageView pageView = PageView.builder()
		// 	.viewCount(14)
		// 	.duration(Duration.ofSeconds(12))
		// 	.build();
		//
		// pageViewRepository.save(pageView);
	}



}
