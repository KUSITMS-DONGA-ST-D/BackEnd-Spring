package com.kusithms.dongastd.domain.content.service;

import com.kusithms.dongastd.domain.content.entity.Content;
import com.kusithms.dongastd.domain.content.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepo;

    public List<LocalDateTime> findContentSchedule() {
        List<Content> contents = contentRepo.findAll();
        List<LocalDateTime> createTimes = new ArrayList<>();

        for (int i = 0; i < contents.size(); i++) {
            createTimes.add(contents.get(i).getCreatedDate());
        }
        return createTimes;
    }
}
