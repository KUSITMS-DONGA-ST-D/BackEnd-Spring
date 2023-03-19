package com.kusithms.dongastd.domain.livebroadcast.dto;

import java.time.LocalDateTime;

public interface LiveScheduleResponse {
    String getLiveUrl();
    String getLiveTitle();
    LocalDateTime getStartTime();
    String getStatus();
}
