package com.kusithms.dongastd.domain.contentdata.dto;

import com.kusithms.dongastd.domain.contentdata.entity.ContentData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class TodayContent {
    private String url;
    private String contentTitle;
    private String createDate;

    @Builder
    public TodayContent(String url, String contentTitle, String createDate) {
        this.url = url;
        this.contentTitle = contentTitle;
        this.createDate = createDate;
    }

    public static TodayContent of(ContentData contentData) {
        return TodayContent.builder()
                .contentTitle(contentData.getContent().getTitle())
                .url(contentData.getUrl())
                .createDate(contentData.getContent().getCreatedDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH")))
                .build();
    }
}
