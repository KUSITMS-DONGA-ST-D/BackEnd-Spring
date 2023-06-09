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
import com.kusithms.dongastd.domain.users.entity.Gender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
            int avgTime = 0;

            List<Comment> comments = commentRepository.findAllByContentAndCreatedDateBetween(content, now.minusDays(1), now);
            List<Interest> interests = interestRepository.findAllByContentAndCreatedDateBetween(content, now.minusDays(1), now);
            List<ContentData> contentDatas = contentDataRepository.findAllByContent(content);

            for (int j = 0; j < contentDatas.size(); j++) {
                avgTime += contentDatas.get(j).getDuration().toMinutesPart();
            }
            if (contentDatas.size() != 0)
                avgTime /= contentDatas.size();

            ContentsRes res = ContentsRes.of(contentDatas.get(0).getUrl(), avgTime, content, interests.size(), comments.size());
            allContentsRes.add(res);
        }

        return allContentsRes;
    }

    public List<ContentsRes> findAverageFilter(String start_day, String end_day, int age, String inputGender, String category) {
        int loop = 0;

        String startDay = start_day + "-00-00";
        String endDay = end_day + "-00-00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

        LocalDateTime startTime = LocalDateTime.parse(startDay, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endDay, formatter);

        Gender gender = getGender(inputGender);

        int startAge = 0;
        startAge = getStartAge(age, startAge);
        int endAge = startAge + 9;


        List<Content> allContents = contentRepo.findAllByCreatedDateBefore(endTime);
        List<ContentsRes> filterContentsRes = new ArrayList<>();

        for (int i = 0; i < allContents.size(); i++) {
            loop++;

            List<ContentData> contentDatas = contentDataRepository.findAllByContent(allContents.get(i));

            int avgTime = getAvgTime(contentDatas);

            List<Comment> comments = commentRepository.findAllByUser_AgeIsBetweenAndUser_GenderAndUser_CategoryAndContentAndCreatedDateBetween(startAge, endAge, gender, category, allContents.get(i), startTime, endTime);
            List<Interest> interests = interestRepository.findAllByUser_AgeIsBetweenAndUser_GenderAndUser_CategoryAndContentAndCreatedDateBetween(startAge, endAge, gender, category, allContents.get(i), startTime, endTime);

            ContentsRes contentsRes = new ContentsRes(contentDatas.get(0).getUrl(), allContents.get(i).getTitle(), allContents.get(i).getCategory(), interests.size(), comments.size(), avgTime);
            filterContentsRes.add(contentsRes);
        }


        log.info("total loop = {}", loop);
        return filterContentsRes;
    }

    public List<ContentsRes> findAverageFilter(String start_day, String end_day, String inputGender, String category) {
        int loop = 0;

        String startDay = start_day + "-00-00";
        String endDay = end_day + "-00-00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

        LocalDateTime startTime = LocalDateTime.parse(startDay, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endDay, formatter);

        log.info("startTime = {}", startTime);
        log.info("endTime = {}", endTime);

        Gender gender = getGender(inputGender);

        List<Content> allContents = contentRepo.findAllByCreatedDateBefore(endTime);
        List<ContentsRes> filterContentsRes = new ArrayList<>();

        for (int i = 0; i < allContents.size(); i++) {
            loop++;

            List<ContentData> contentDatas = contentDataRepository.findAllByContent(allContents.get(i));

            int avgTime = getAvgTime(contentDatas);

            List<Comment> comments = commentRepository.findAllByUser_GenderAndUser_CategoryAndContentAndCreatedDateBetween(gender, category, allContents.get(i), startTime, endTime);
            List<Interest> interests = interestRepository.findAllByUser_GenderAndUser_CategoryAndContentAndCreatedDateBetween(gender, category, allContents.get(i), startTime, endTime);

            ContentsRes contentsRes = new ContentsRes("test", allContents.get(i).getTitle(), allContents.get(i).getCategory(), interests.size(), comments.size(), avgTime);
            filterContentsRes.add(contentsRes);
        }


        log.info("total loop = {}", loop);
        return filterContentsRes;
    }

    public List<ContentsRes> findAverageFilter(String start_day, String end_day, int age, String category) {
        int loop = 0;


        String startDay = start_day + "-00-00";
        String endDay = end_day + "-00-00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

        LocalDateTime startTime = LocalDateTime.parse(startDay, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endDay, formatter);

        log.info("startTime = {}", startTime);
        log.info("endTime = {}", endTime);

        int startAge = 0;
        startAge = getStartAge(age, startAge);
        int endAge = startAge + 9;

        List<Content> allContents = contentRepo.findAllByCreatedDateBefore(endTime);
        List<ContentsRes> filterContentsRes = new ArrayList<>();

        for (int i = 0; i < allContents.size(); i++) {
            loop++;

            List<ContentData> contentDatas = contentDataRepository.findAllByContent(allContents.get(i));

            int avgTime = getAvgTime(contentDatas);

            List<Comment> comments = commentRepository.findAllByUser_AgeIsBetweenAndUser_CategoryAndContentAndCreatedDateBetween(startAge, endAge, category, allContents.get(i), startTime, endTime);
            List<Interest> interests = interestRepository.findAllByUser_AgeIsBetweenAndUser_CategoryAndContentAndCreatedDateBetween(startAge, endAge, category, allContents.get(i), startTime, endTime);

            ContentsRes contentsRes = new ContentsRes("test", allContents.get(i).getTitle(), allContents.get(i).getCategory(), interests.size(), comments.size(), avgTime);
            filterContentsRes.add(contentsRes);
        }

        log.info("total loop = {}", loop);
        return filterContentsRes;
    }

    public List<ContentsRes> findAverageFilterV2(String start_day, String end_day, int age, String inputGender) {
        int loop = 0;

        String startDay = start_day + "-00-00";
        String endDay = end_day + "-00-00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

        LocalDateTime startTime = LocalDateTime.parse(startDay, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endDay, formatter);

        log.info("startTime = {}", startTime);
        log.info("endTime = {}", endTime);

        Gender gender = getGender(inputGender);

        int startAge = 0;
        startAge = getStartAge(age, startAge);
        int endAge = startAge + 9;

        List<Content> allContents = contentRepo.findAllByCreatedDateBefore(endTime);
        List<ContentsRes> filterContentsRes = new ArrayList<>();

        for (int i = 0; i < allContents.size(); i++) {
            loop++;

            List<ContentData> contentDatas = contentDataRepository.findAllByContent(allContents.get(i));

            int avgTime = getAvgTime(contentDatas);

            List<Comment> comments = commentRepository.findAllByUser_AgeIsBetweenAndUser_GenderAndContentAndCreatedDateBetween(startAge, endAge, gender, allContents.get(i), startTime, endTime);
            List<Interest> interests = interestRepository.findAllByUser_AgeIsBetweenAndUser_GenderAndContentAndCreatedDateBetween(startAge, endAge, gender, allContents.get(i), startTime, endTime);

            ContentsRes contentsRes = new ContentsRes(contentDatas.get(0).getUrl(), allContents.get(i).getTitle(), allContents.get(i).getCategory(), interests.size(), comments.size(), avgTime);
            filterContentsRes.add(contentsRes);
        }


        log.info("total loop = {}", loop);
        return filterContentsRes;
    }

    public List<ContentsRes> findAverageFilter(String start_day, String end_day, int age) {
        int loop = 0;

        String startDay = start_day + "-00-00";
        String endDay = end_day + "-00-00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

        LocalDateTime startTime = LocalDateTime.parse(startDay, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endDay, formatter);

        int startAge = 0;
        startAge = getStartAge(age, startAge);
        int endAge = startAge + 9;

        List<Content> allContents = contentRepo.findAllByCreatedDateBefore(endTime);
        List<ContentsRes> filterContentsRes = new ArrayList<>();

        for (int i = 0; i < allContents.size(); i++) {
            loop++;

            List<ContentData> contentDatas = contentDataRepository.findAllByContent(allContents.get(i));

            int avgTime = getAvgTime(contentDatas);

            List<Comment> comments = commentRepository.findAllByUser_AgeIsBetweenAndContentAndCreatedDateBetween(startAge, endAge, allContents.get(i), startTime, endTime);
            List<Interest> interests = interestRepository.findAllByUser_AgeIsBetweenAndContentAndCreatedDateBetween(startAge, endAge, allContents.get(i), startTime, endTime);

            ContentsRes contentsRes = new ContentsRes(contentDatas.get(0).getUrl(), allContents.get(i).getTitle(), allContents.get(i).getCategory(), interests.size(), comments.size(), avgTime);
            filterContentsRes.add(contentsRes);
        }


        log.info("total loop = {}", loop);
        return filterContentsRes;
    }

    public List<ContentsRes> findAverageFilter(String start_day, String end_day, String inputGender) {
        int loop = 0;

        String startDay = start_day + "-00-00";
        String endDay = end_day + "-00-00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

        LocalDateTime startTime = LocalDateTime.parse(startDay, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endDay, formatter);

        Gender gender = getGender(inputGender);

        List<Content> allContents = contentRepo.findAllByCreatedDateBefore(endTime);
        List<ContentsRes> filterContentsRes = new ArrayList<>();

        for (int i = 0; i < allContents.size(); i++) {
            loop++;

            List<ContentData> contentDatas = contentDataRepository.findAllByContent(allContents.get(i));

            int avgTime = getAvgTime(contentDatas);

            List<Comment> comments = commentRepository.findAllByUser_GenderAndContentAndCreatedDateBetween(gender, allContents.get(i), startTime, endTime);
            List<Interest> interests = interestRepository.findAllByUser_GenderAndContentAndCreatedDateBetween(gender, allContents.get(i), startTime, endTime);

            ContentsRes contentsRes = new ContentsRes(contentDatas.get(0).getUrl(), allContents.get(i).getTitle(), allContents.get(i).getCategory(), interests.size(), comments.size(), avgTime);
            filterContentsRes.add(contentsRes);
        }


        log.info("total loop = {}", loop);
        return filterContentsRes;
    }

    public List<ContentsRes> findAverageFilterV2(String start_day, String end_day, String category) {
        int loop = 0;

        String startDay = start_day + "-00-00";
        String endDay = end_day + "-00-00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

        LocalDateTime startTime = LocalDateTime.parse(startDay, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endDay, formatter);

        List<Content> allContents = contentRepo.findAllByCreatedDateBefore(endTime);
        List<ContentsRes> filterContentsRes = new ArrayList<>();


        for (int i = 0; i < allContents.size(); i++) {
            loop++;

            List<ContentData> contentDatas = contentDataRepository.findAllByContent(allContents.get(i));

            int avgTime = getAvgTime(contentDatas);

            List<Comment> comments = commentRepository.findAllByUser_CategoryAndContentAndCreatedDateBetween(category, allContents.get(i), startTime, endTime);

            List<Interest> interests = interestRepository.findAllByUser_CategoryAndContentAndCreatedDateBetween(category, allContents.get(i), startTime, endTime);

            ContentsRes contentsRes = new ContentsRes("test", allContents.get(i).getTitle(), allContents.get(i).getCategory(), interests.size(), comments.size(), avgTime);
            filterContentsRes.add(contentsRes);
        }

        log.info("total loop = {}", loop);
        return filterContentsRes;
    }

    public List<ContentsRes> findAverageFilter(String start_day, String end_day) {
        int loop = 0;

        String startDay = start_day + "-00-00";
        String endDay = end_day + "-00-00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

        LocalDateTime startTime = LocalDateTime.parse(startDay, formatter);
        LocalDateTime endTime = LocalDateTime.parse(endDay, formatter);

        List<Content> allContents = contentRepo.findAllByCreatedDateBefore(endTime);
        List<ContentsRes> filterContentsRes = new ArrayList<>();

        for (int i = 0; i < allContents.size(); i++) {
            loop++;

            List<ContentData> contentDatas = contentDataRepository.findAllByContent(allContents.get(i));

            int avgTime = getAvgTime(contentDatas);

            List<Comment> comments = commentRepository.findAllByContentAndCreatedDateBetween(allContents.get(i), startTime, endTime);
            List<Interest> intersts = interestRepository.findAllByContentAndCreatedDateBetween(allContents.get(i), startTime, endTime);
            ContentsRes contentsRes = new ContentsRes(contentDatas.get(0).getUrl(), allContents.get(i).getTitle(), allContents.get(i).getCategory(), intersts.size(), comments.size(), avgTime);
            filterContentsRes.add(contentsRes);
        }


        log.info("total loop = {}", loop);
        return filterContentsRes;
    }

    private int getStartAge(int age, int startAge) {
        if (20 <= age && age < 30) {
            startAge = 20;
        }
        if (30 <= age && age < 40) {
            startAge = 30;
        }
        if (40 <= age && age < 50) {
            startAge = 40;
        }
        if (50 <= age && age < 60) {
            startAge = 50;
        }
        if (60 <= age && age < 70) {
            startAge = 60;
        }
        if (70 <= age && age < 80) {
            startAge = 70;
        }
        return startAge;
    }

    private Gender getGender(String inputGender) {
        if (inputGender.equals("MALE")) {
            return Gender.MALE;
        }
        return Gender.FEMALE;
    }

    private int getAvgTime(List<ContentData> contentDatas) {
        int avgTime = 0;

        for (int j = 0; j < contentDatas.size(); j++) {
            avgTime += contentDatas.get(j).getDuration().toMinutesPart();
        }
        if (contentDatas.size() != 0)
            avgTime /= contentDatas.size();

        return avgTime;
    }
}
