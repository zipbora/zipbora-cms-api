package com.zipbom.zipbom.RecentView.repository;

import com.zipbom.zipbom.RecentView.model.RecentView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecentViewRepository extends JpaRepository<RecentView, Integer> {
}
