package com.kusithms.dongastd.domain.content.dto;

import com.kusithms.dongastd.domain.content.entity.Content;
import com.kusithms.dongastd.domain.contentdata.entity.ContentData;
import jdk.jfr.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Builder
@AllArgsConstructor
@Slf4j
public class ContentsRes {
    private String url;
    private String contentTitle;
    private String category;
    private int interest;
    private int comment;
    private int averageViewTime;

    public static ContentsRes of(String url, int averageViewTime, Content content, int interest, int comment) {
        return ContentsRes.builder()
                .url(url)
                .contentTitle(content.getTitle())
                .interest(interest)
                .comment(comment)
                .category(content.getCategory())
                .averageViewTime(averageViewTime)
                .build();
    }
}
