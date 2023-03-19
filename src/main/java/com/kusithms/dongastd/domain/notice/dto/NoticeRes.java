package com.kusithms.dongastd.domain.notice.dto;

import com.kusithms.dongastd.domain.notice.entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeRes {
    private Long noticeId;
    private String noticeTitle;
    private String username;
    private LocalDate createdDate;

    @Builder
    public NoticeRes(Long noticeId, String noticeTitle, String username, LocalDateTime createDate) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.username = username;
        this.createdDate = createDate.toLocalDate();
    }

    public static NoticeRes of(Notice notice) {
        return NoticeRes.builder()
                .noticeId(notice.getId())
                .noticeTitle(notice.getTitle())
                .username(notice.getUser().getUsername())
                .createDate(notice.getCreatedDate())
                .build();
    }
}
