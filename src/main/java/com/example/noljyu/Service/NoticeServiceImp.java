package com.example.noljyu.Service;

import com.example.noljyu.Entity.NoticeEntity;
import com.example.noljyu.Repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImp implements NoticeService{
    @Autowired
    NoticeRepository noticeRepository;
    @Override
    public void nsave(NoticeEntity net) {
        noticeRepository.save(net);
    }

    @Override
    public NoticeEntity nout() {
        return noticeRepository.nout();
    }
}
