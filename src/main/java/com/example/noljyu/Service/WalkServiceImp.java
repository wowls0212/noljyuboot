package com.example.noljyu.Service;

import com.example.noljyu.DTO.WalkDTO;
import com.example.noljyu.Entity.WalkEntity;
import com.example.noljyu.Entity.WalkWriteEntity;
import com.example.noljyu.Repository.WalkRepository;
import com.example.noljyu.Repository.WalkWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalkServiceImp implements WalkService{
    @Autowired
    WalkRepository walkRepository;
    @Autowired
    WalkWriteRepository walkWriteRepository;

    @Override
    public void walkinput(WalkEntity entity) {
        walkRepository.save(entity);
    }

    @Override
    public List<WalkEntity> walkout() {
        return walkRepository.findAll();
    }

    @Override
    public WalkEntity getWalkDetail(long walknum) {
        return walkRepository.findById(walknum).orElse(null);
    }

    @Override
    public WalkEntity walkdelete(long walknum) {
        WalkEntity wentity = walkRepository.findById(walknum).orElse(null);
        if (wentity != null) {
            walkRepository.deleteById(walknum);
        }
        return wentity;
    }

    @Override
    public List<WalkDTO> walksearch(String walkkey, String walkvalue) {
        if ("title".equalsIgnoreCase(walkkey)) {
            return walkRepository.searchByWalktitle(walkvalue);
        } else if ("content".equalsIgnoreCase(walkkey)) {
            return walkRepository.searchByWalkdetail(walkvalue);
        } else {
            return List.of();
        }
    }

    @Override
    public void walkcnt(Long walknum) {
        WalkEntity wentity = walkRepository.findById(walknum).orElse(null);
        if (wentity != null) {
            int currentCount = wentity.getWalkcnt(); // 예: 조회수 필드명
            wentity.setWalkcnt(currentCount + 1);
            walkRepository.save(wentity);
        }
    }

    @Override
    public void walkupdate(WalkEntity entity) { walkRepository.save(entity);
    }

    @Override
    public void wwr(WalkWriteEntity wwr) {
        walkWriteRepository.save(wwr);
    }

    @Override
    public List<WalkWriteEntity> walklist() {
        return walkWriteRepository.findAll();
    }

}
