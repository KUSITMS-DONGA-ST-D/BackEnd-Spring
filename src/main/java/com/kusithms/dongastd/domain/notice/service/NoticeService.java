package com.kusithms.dongastd.domain.notice.service;

import com.kusithms.dongastd.domain.notice.dto.NoticeRes;
import com.kusithms.dongastd.domain.notice.entity.Notice;
import com.kusithms.dongastd.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public List<NoticeRes> allNotices() {
        List<NoticeRes> result = new ArrayList<>();
        List<Notice> getNotices = noticeRepository.findAll();

        for (int i = 0; i < getNotices.size(); i++) {
            result.add(NoticeRes.of(getNotices.get(i)));
        }

        return result;
    }
}
