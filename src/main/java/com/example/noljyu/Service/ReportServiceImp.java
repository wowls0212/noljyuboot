package com.example.noljyu.Service;

import com.example.noljyu.Entity.ReportEntity;
import com.example.noljyu.Repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImp implements ReportService{
    @Autowired
    ReportRepository reportRepository;
    @Override
    public void rsave(ReportEntity pentity) {
        reportRepository.save(pentity);
    }

    @Override
    public List<ReportEntity> rout() {
        return reportRepository.findAll();
    }
}
