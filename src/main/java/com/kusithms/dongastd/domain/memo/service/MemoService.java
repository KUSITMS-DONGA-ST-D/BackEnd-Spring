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
    private Map<String, Object> result = new HashMap<>();
    private List<String> contents = new ArrayList<>();
    private LocalDateTime today = LocalDate.now().atStartOfDay();

    public Map<String, Object> findYesterday() {
        LocalDateTime start = today.minusDays(1);

        return getResult(start, today);
    }

    public Map<String, Object> findToday() {
        LocalDateTime end = today.plusDays(1);

        return getResult(today, end);
    }

    public Map<String, Object> findTomorrow() {
        LocalDateTime start = today.plusDays(1);
        LocalDateTime end = today.plusDays(2);

        return getResult(start, end);
    }

    private Map<String, Object> getResult(LocalDateTime start, LocalDateTime end) {
        List<Memo> tomorrowMemo = memoRepository.findAllByCreatedDateBetween(start, end);

        for (int i = 0; i < tomorrowMemo.size(); i++) {
            contents.add(tomorrowMemo.get(i).getContent());
        }

        if (!tomorrowMemo.isEmpty())
            result.put("date", tomorrowMemo.get(0).getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        result.put("contents", contents);

        return result;
    }
}
