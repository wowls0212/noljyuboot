package com.example.noljyu.Repository;

import com.example.noljyu.Entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity,Long> {
}
