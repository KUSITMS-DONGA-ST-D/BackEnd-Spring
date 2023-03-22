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
import com.kusithms.dongastd.domain.users.entity.User;
import com.kusithms.dongastd.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final UserRepository userRepository;

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

            ContentData contentData = contentDataRepository.findByContent(content);
            List<Comment> comments = commentRepository.findAllByContentAndCreatedDateBetween(content, now.minusDays(1), now);
            List<Interest> interests = interestRepository.findAllByContentAndCreatedDateBetween(content, now.minusDays(1), now);

            ContentsRes res = ContentsRes.of(contentData, content, interests.size(), comments.size());
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

        log.info("startTime = {}", startTime);
        log.info("endTime = {}", endTime);
        Gender gender;

        if (inputGender.equals("MALE")) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }
        int startAge = 0;
        int endAge = 0;
        if (20 <= age && age < 30) {
            startAge = 20;
            endAge = 29;
        }
        if (30 <= age && age < 40) {
            startAge = 30;
            endAge = 39;
        }
        if (40 <= age && age < 50) {
            startAge = 40;
            endAge = 49;
        }
        if (50 <= age && age < 60) {
            startAge = 50;
            endAge = 59;
        }
        if (60 <= age && age < 70) {
            startAge = 60;
            endAge = 69;
        }
        if (70 <= age && age < 80) {
            startAge = 70;
            endAge = 79;
        }
        if (80 <= age && age < 90) {
            startAge = 80;
            endAge = 89;
        }

        List<User> filterUser = userRepository.findAllByAgeIsBetweenAndGenderAndCategory(startAge, endAge, gender, category);
        List<Content> allContents = contentRepo.findAllByCreatedDateBeforeAndCategory(endTime, category);
        List<ContentsRes> filterContentsRes = new ArrayList<>();

        log.info("gender = {}", gender);

        log.info("allContents Size = {}", allContents.size());

        log.info("filterUser size = {}", filterUser.size());

        for (int i = 0; i < allContents.size(); i++) {
            loop++;

            log.info("들어옴 {}", allContents.get(i).toString());
            int comment = 0;
            int interest = 0;
            int avgTime = 0;

            ContentData contentData = contentDataRepository.findByContent(allContents.get(i));
            avgTime = contentData.getDuration().toMinutesPart();

            for (int j = 0; j < filterUser.size(); j++) {
                List<Comment> filterComment = commentRepository.findAllByUserAndContentAndCreatedDateBetween(filterUser.get(j), allContents.get(i), startTime, endTime);

                loop++;

                for (int z = 0; z < filterComment.size(); z++) {
                    comment++;

                    loop++;
                }

                Optional<Interest> filterInterest = interestRepository.findByUserAndContentAndCreatedDateBetween(filterUser.get(j), allContents.get(i), startTime, endTime);

                if (filterInterest.isPresent())
                    interest++;

                interestRepository.findByUserAndContentAndCreatedDateBetween(filterUser.get(j), allContents.get(i), startTime, endTime);
            }
            ContentsRes contentsRes = new ContentsRes("test", allContents.get(i).getTitle(), allContents.get(i).getCategory(), interest, comment, avgTime);
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
        Gender gender;

        if (inputGender.equals("MALE")) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }

        List<User> filterUser = userRepository.findAllByGenderAndCategory(gender, category);
        List<Content> allContents = contentRepo.findAllByCreatedDateBeforeAndCategory(endTime, category);
        List<ContentsRes> filterContentsRes = new ArrayList<>();

        log.info("gender = {}", gender);

        log.info("allContents Size = {}", allContents.size());

        log.info("filterUser size = {}", filterUser.size());

        for (int i = 0; i < allContents.size(); i++) {
            loop++;

            log.info("들어옴 {}", allContents.get(i).toString());
            int comment = 0;
            int interest = 0;
            int avgTime = 0;

            ContentData contentData = contentDataRepository.findByContent(allContents.get(i));
            avgTime = contentData.getDuration().toMinutesPart();

            for (int j = 0; j < filterUser.size(); j++) {
                List<Comment> filterComment = commentRepository.findAllByUserAndContentAndCreatedDateBetween(filterUser.get(j), allContents.get(i), startTime, endTime);

                loop++;

                for (int z = 0; z < filterComment.size(); z++) {
                    comment++;

                    loop++;
                }

                Optional<Interest> filterInterest = interestRepository.findByUserAndContentAndCreatedDateBetween(filterUser.get(j), allContents.get(i), startTime, endTime);

                if (filterInterest.isPresent())
                    interest++;

                interestRepository.findByUserAndContentAndCreatedDateBetween(filterUser.get(j), allContents.get(i), startTime, endTime);
            }
            ContentsRes contentsRes = new ContentsRes("test", allContents.get(i).getTitle(), allContents.get(i).getCategory(), interest, comment, avgTime);
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
        int endAge = 0;
        if (20 <= age && age < 30) {
            startAge = 20;
            endAge = 29;
        }
        if (30 <= age && age < 40) {
            startAge = 30;
            endAge = 39;
        }
        if (40 <= age && age < 50) {
            startAge = 40;
            endAge = 49;
        }
        if (50 <= age && age < 60) {
            startAge = 50;
            endAge = 59;
        }
        if (60 <= age && age < 70) {
            startAge = 60;
            endAge = 69;
        }
        if (70 <= age && age < 80) {
            startAge = 70;
            endAge = 79;
        }
        if (80 <= age && age < 90) {
            startAge = 80;
            endAge = 89;
        }

        List<User> filterUser = userRepository.findAllByAgeIsBetweenAndCategory(startAge, endAge, category);
        List<Content> allContents = contentRepo.findAllByCreatedDateBeforeAndCategory(endTime, category);
        List<ContentsRes> filterContentsRes = new ArrayList<>();

        log.info("allContents Size = {}", allContents.size());

        log.info("filterUser size = {}", filterUser.size());

        for (int i = 0; i < allContents.size(); i++) {
            loop++;

            log.info("들어옴 {}", allContents.get(i).toString());
            int comment = 0;
            int interest = 0;
            int avgTime = 0;

            ContentData contentData = contentDataRepository.findByContent(allContents.get(i));
            avgTime = contentData.getDuration().toMinutesPart();

            for (int j = 0; j < filterUser.size(); j++) {
                List<Comment> filterComment = commentRepository.findAllByUserAndContentAndCreatedDateBetween(filterUser.get(j), allContents.get(i), startTime, endTime);

                loop++;

                for (int z = 0; z < filterComment.size(); z++) {
                    comment++;

                    loop++;
                }

                Optional<Interest> filterInterest = interestRepository.findByUserAndContentAndCreatedDateBetween(filterUser.get(j), allContents.get(i), startTime, endTime);

                if (filterInterest.isPresent())
                    interest++;

                interestRepository.findByUserAndContentAndCreatedDateBetween(filterUser.get(j), allContents.get(i), startTime, endTime);
            }
            ContentsRes contentsRes = new ContentsRes("test", allContents.get(i).getTitle(), allContents.get(i).getCategory(), interest, comment, avgTime);
            filterContentsRes.add(contentsRes);
        }


        log.info("total loop = {}", loop);
        return filterContentsRes;
    }
}
