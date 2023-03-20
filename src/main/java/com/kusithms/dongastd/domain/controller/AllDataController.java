package com.kusithms.dongastd.domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kusithms.dongastd.domain.content.service.ContentService;
import com.kusithms.dongastd.domain.contentdata.dto.TodayContent;
import com.kusithms.dongastd.domain.contentdata.service.ContentDataService;
import com.kusithms.dongastd.domain.controller.dto.Phase1;
import com.kusithms.dongastd.domain.controller.dto.Phase3;
import com.kusithms.dongastd.domain.livebroadcast.dto.TodayLive;
import com.kusithms.dongastd.domain.livebroadcast.service.LiveBroadcastService;
import com.kusithms.dongastd.domain.memo.service.MemoService;
import com.kusithms.dongastd.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AllDataController {
    private final MainController mainController;
    private final LiveBroadcastService liveBroadcastService;
    private final ContentDataService contentDataService;
    private final ContentService contentService;
    private final MemoService memoService;
    private final NoticeService noticeService;

    @GetMapping("/all-data")
    public String getAllData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> schedule_calendar = new HashMap<>();
        Map<String, Object> schedule_today = new HashMap<>();
        Map<String, Object> memo = new HashMap<>();

        List<String> live_broadcast = liveBroadcastService.findLiveSchedule();
        List<String> content = contentService.findContentSchedule();

        List<TodayLive> today_live_broadcast = liveBroadcastService.findTodayLive();
        List<TodayContent> today_content = contentDataService.findTodaySchedule();

        schedule_calendar.put("content", content);
        schedule_calendar.put("live_broadcast", live_broadcast);

        schedule_today.put("today_content", today_content);
        schedule_today.put("today_live_broadcast", today_live_broadcast);

        memo.put("yesterday", memoService.findYesterday());
        memo.put("today", memoService.findToday());
        memo.put("tomorrow", memoService.findTomorrow());

        result.put("schedule_calendar", schedule_calendar);
        result.put("schedule_today", schedule_today);
        result.put("memo", memo);
        result.put("notice", noticeService.allNotices());
        result.put("content", contentService.findAverageContent());

        List<Phase1> phase1 = mainController.totalGrowthBarChartPhase1(1);
        List<Phase3> phase3 = mainController.totalGrowthBarChartPhase3(1);

        result.put("phase1", phase1);
        result.put("phase3", phase3);

        String resultJson = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(result);
        return resultJson;
    }

}
