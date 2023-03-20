package com.kusithms.dongastd.domain.livebroadcast.dto;

import com.kusithms.dongastd.domain.livebroadcast.entity.LiveBroadcast;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class TodayLive {
    private String liveUrl;
    private String liveTitle;
    private String startTime;
    private String status;

    @Builder
    public TodayLive(String liveUrl, String liveTitle, LocalDateTime startTime, String status) {
        this.liveTitle = liveTitle;
        this.liveUrl = liveUrl;
        this.startTime = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH"));
        this.status = status;
    }

    public static TodayLive of(LiveBroadcast liveBroadcast) {
        String status = "yet";
        if (LocalDateTime.now().isAfter(liveBroadcast.getEndTime()))
            status = "finish";

        return TodayLive.builder()
                .liveTitle(liveBroadcast.getTitle())
                .liveUrl(liveBroadcast.getUrl())
                .startTime(liveBroadcast.getStartTime())
                .status(status)
                .build();
    }
}
