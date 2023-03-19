package com.kusithms.dongastd.domain.contentdata.service;

import com.kusithms.dongastd.domain.contentdata.dto.TodayContent;
import com.kusithms.dongastd.domain.contentdata.entity.ContentData;
import com.kusithms.dongastd.domain.contentdata.repository.ContentDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentDataService {
    private final ContentDataRepository contentDataRepository;

    public List<TodayContent> findTodaySchedule() {
        List<TodayContent> todayContents = new ArrayList<>();

        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);

        List<ContentData> contentDatas = contentDataRepository.findByContent_CreatedDateBetween(start, end);
        for (int i = 0; i < contentDatas.size(); i++) {
            todayContents.add(TodayContent.of(contentDatas.get(i)));
        }

        return todayContents;
    }
}
