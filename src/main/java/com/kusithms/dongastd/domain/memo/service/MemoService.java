package com.kusithms.dongastd.domain.memo.service;

import com.kusithms.dongastd.domain.memo.entity.Memo;
import com.kusithms.dongastd.domain.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private LocalDateTime today = LocalDate.now().atStartOfDay();

    public Map<String, Object> findYesterday() {
        Map<String, Object> result = new HashMap<>();

        LocalDateTime start = today.minusDays(1);

        return getResult(result, start, today);
    }

    public Map<String, Object> findToday() {
        Map<String, Object> result = new HashMap<>();

        LocalDateTime end = today.plusDays(1);

        return getResult(result, today, end);
    }

    public Map<String, Object> findTomorrow() {
        Map<String, Object> result = new HashMap<>();

        LocalDateTime start = today.plusDays(1);
        LocalDateTime end = today.plusDays(2);

        return getResult(result, start, end);
    }

    private Map<String, Object> getResult(Map<String, Object> result, LocalDateTime start, LocalDateTime end) {
        List<Memo> memos = memoRepository.findAllByCreatedDateBetween(start, end);
        List<String> contents = new ArrayList<>();

        for (int i = 0; i < memos.size(); i++) {
            contents.add(memos.get(i).getContent());
        }

        if (!memos.isEmpty())
            result.put("date", memos.get(0).getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        result.put("contents", contents);

        return result;
    }
}
