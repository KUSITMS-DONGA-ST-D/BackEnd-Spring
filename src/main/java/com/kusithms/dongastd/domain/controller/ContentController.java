package com.kusithms.dongastd.domain.controller;

import com.kusithms.dongastd.domain.content.dto.ContentsRes;
import com.kusithms.dongastd.domain.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContentController {
    private final ContentService contentService;

    @GetMapping("filterd")
    public Map<String, Object> filtered(String start_day, String end_day, int age, String gender, String category) {
        List<ContentsRes> averageFilter = contentService.findAverageFilter(start_day, end_day, age, gender, category);
        Map<String, Object> result = new HashMap<>();
        result.put("result", averageFilter);

        return result;
    }
}
