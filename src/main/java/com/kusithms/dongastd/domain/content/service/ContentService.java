package com.kusithms.dongastd.domain.content.service;

import com.kusithms.dongastd.domain.comment.entity.Comment;
import com.kusithms.dongastd.domain.comment.respository.CommentRepository;
import com.kusithms.dongastd.domain.content.dto.ContentsRes;
import com.kusithms.dongastd.domain.content.entity.Content;
import com.kusithms.dongastd.domain.content.repository.ContentRepository;
import com.kusithms.dongastd.domain.contentdata.entity.ContentData;
import com.kusithms.dongastd.domain.contentdata.repository.ContentDataRepository;
import com.kusithms.dongastd.domain.interest.entity.Interest;
import com.kusithms.dongastd.domain.interest.repository.InterestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentService {
    private final ContentRepository contentRepo;
    private final ContentDataRepository contentDataRepository;
    private final CommentRepository commentRepository;
    private final InterestRepository interestRepository;

    public List<String> findContentSchedule() {
        List<Content> contents = contentRepo.findAll();
        List<String> createTimes = new ArrayList<>();

        for (int i = 0; i < contents.size(); i++) {
            String time = contents.get(i).getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            createTimes.add(time);
        }
        return createTimes;
    }

    public List<ContentsRes> findAverageContent() {
        List<Content> allContents = contentRepo.findAll();
        List<ContentsRes> allContentsRes = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < allContents.size(); i++) {
            Content content = allContents.get(i);

            ContentData contentData = contentDataRepository.findByCreatedDateBetweenAndContent(now.minusDays(1), now, content);
            List<Comment> comments = commentRepository.findAllByContent(content);
            List<Interest> interests = interestRepository.findAllByContent(content);

            ContentsRes res = ContentsRes.of(contentData, content, interests.size(), comments.size());
            allContentsRes.add(res);
        }

        return allContentsRes;
    }
}
