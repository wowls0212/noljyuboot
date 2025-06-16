package com.example.noljyu.Service;

import com.example.noljyu.Entity.ReportEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {
    void rsave(ReportEntity pentity);

    List<ReportEntity> rout();
}
