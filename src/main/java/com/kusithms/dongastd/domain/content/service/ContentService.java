package com.kusithms.dongastd.domain.content.service;

import com.kusithms.dongastd.domain.content.entity.Content;
import com.kusithms.dongastd.domain.content.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepo;

    public List<String> findContentSchedule() {
        List<Content> contents = contentRepo.findAll();
        List<String> createTimes = new ArrayList<>();

        for (int i = 0; i < contents.size(); i++) {
            String time = contents.get(i).getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            createTimes.add(time);
        }
        return createTimes;
    }
}
