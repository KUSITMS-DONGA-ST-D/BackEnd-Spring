package com.kusithms.dongastd.domain.livebroadcast.service;

import com.kusithms.dongastd.domain.livebroadcast.dto.LiveScheduleResponse;
import com.kusithms.dongastd.domain.livebroadcast.entity.LiveBroadcast;
import com.kusithms.dongastd.domain.livebroadcast.repository.LiveBroadCastRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LiveBroadcastService {
    private final LiveBroadCastRepo liveBroadCastRepo;

    public List<LocalDateTime> findLiveSchedule() {
        List<LiveBroadcast> liveBroadcasts = liveBroadCastRepo.findAll();
        List<LocalDateTime> liveStartTimes = new ArrayList<>();

        for (int i = 0; i < liveBroadcasts.size(); i++) {
            liveStartTimes.add(liveBroadcasts.get(i).getStartTime());
        }

        return liveStartTimes;
    }

    public List<LiveScheduleResponse> findTodayLive() {
        LocalDate now = LocalDate.now();
        LocalDate yesterDay = now.minusDays(1);
        List<LiveScheduleResponse> todayLives = liveBroadCastRepo.findAllByStartTimeBetween(yesterDay, now);
        return todayLives;
    }
}
