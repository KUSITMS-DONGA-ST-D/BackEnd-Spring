package com.kusithms.dongastd.domain.notice.dto;

import com.kusithms.dongastd.domain.notice.entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class NoticeRes {
    private Long noticeId;
    private String noticeTitle;
    private String username;
    private String createdDate;

    @Builder
    public NoticeRes(Long noticeId, String noticeTitle, String username, String createDate) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.username = username;
        this.createdDate = createDate;
    }

    public static NoticeRes of(Notice notice) {
        return NoticeRes.builder()
                .noticeId(notice.getId())
                .noticeTitle(notice.getTitle())
                .username(notice.getUser().getUsername())
                .createDate(notice.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
