package com.kusithms.dongastd.domain.contentdata.service;

import com.kusithms.dongastd.domain.contentdata.entity.ContentData;
import com.kusithms.dongastd.domain.contentdata.repository.ContentDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentDataService {
    private final ContentDataRepository contentDataRepository;

    public List<ContentData> findTodaySchedule() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        List<ContentData> contentDatas = contentDataRepository.findByCreatedDateBetween(yesterday, today);

        return contentDatas;
    }
}
