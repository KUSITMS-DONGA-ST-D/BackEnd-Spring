package com.kusithms.dongastd.domain.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kusithms.dongastd.domain.content.dto.ContentsRes;
import com.kusithms.dongastd.domain.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContentController {
    private final ContentService contentService;

    @GetMapping("filterd")
    public String filtered(String start_day, String end_day, @RequestParam(required = false) Integer age, String gender, String category) throws JsonProcessingException {
        List<ContentsRes> averageFilter;
        if (age == null) {
            averageFilter = contentService.findAverageFilter(start_day, end_day, gender, category);
        } else {
            averageFilter = contentService.findAverageFilter(start_day, end_day, age, gender, category);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("result", averageFilter);

        ObjectMapper objectMapper = new ObjectMapper();

        String resultJson = objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(result);

        return resultJson;
    }
}
