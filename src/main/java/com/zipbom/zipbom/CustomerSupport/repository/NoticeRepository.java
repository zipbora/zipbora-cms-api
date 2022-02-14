package com.zipbom.zipbom.CustomerSupport.repository;

import com.zipbom.zipbom.CustomerSupport.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
