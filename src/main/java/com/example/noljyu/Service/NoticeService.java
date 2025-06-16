package com.example.noljyu.Service;

import com.example.noljyu.Entity.NoticeEntity;
import org.springframework.stereotype.Service;

@Service
public interface NoticeService {
    void nsave(NoticeEntity net);

    NoticeEntity nout();
}
