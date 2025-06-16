package com.example.noljyu.Service;

import com.example.noljyu.DTO.WalkDTO;
import com.example.noljyu.Entity.WalkEntity;
import com.example.noljyu.Entity.WalkWriteEntity;

import java.util.List;

public interface WalkService {
    void walkinput(WalkEntity entity);

    List<WalkEntity> walkout();

    WalkEntity getWalkDetail(long walknum);

    WalkEntity walkdelete(long walknum);

    List<WalkDTO> walksearch(String walkkey, String walkvalue);

    void walkcnt(Long walknum);

    void walkupdate(WalkEntity entity);


    void wwr(WalkWriteEntity wwr);

    List<WalkWriteEntity> walklist();
}
