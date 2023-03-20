package com.kusithms.dongastd.domain.controller;

import com.kusithms.dongastd.domain.content.service.ContentService;
import com.kusithms.dongastd.domain.contentdata.dto.TodayContent;
import com.kusithms.dongastd.domain.contentdata.service.ContentDataService;
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
    private final LiveBroadcastService liveBroadcastService;
    private final ContentDataService contentDataService;
    private final ContentService contentService;
    private final MemoService memoService;
    private final NoticeService noticeService;

    @GetMapping("/all-data")
    public Map<String, Object> getAllData() {
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

        return result;
    }
}
