package com.kusithms.dongastd.domain.livebroadcast.repository;

import com.kusithms.dongastd.domain.livebroadcast.entity.LiveBroadcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveBroadCastRepo extends JpaRepository<LiveBroadcast, Long> {
}
