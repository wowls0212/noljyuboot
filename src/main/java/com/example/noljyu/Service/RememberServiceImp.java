package com.example.noljyu.Service;

import com.example.noljyu.Entity.LetterEntity;
import com.example.noljyu.Entity.RememberEntity;
import com.example.noljyu.Repository.LetterRepository;
import com.example.noljyu.Repository.RememberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RememberServiceImp implements RememberService {
    @Autowired
    RememberRepository rememberRepository;
    @Autowired
    LetterRepository letterRepository;


    @Override
    public List<RememberEntity> findAll() {
        return rememberRepository.findAll(Sort.by(Sort.Direction.DESC, "letternum"));  // 최신순 정렬
    }

    @Override
    public RememberEntity getLetterDetail(String loginId) {
        return rememberRepository.findById(loginId)
                .orElseThrow(() -> new EntityNotFoundException("해당 편지를 찾을 수 없습니다. id=" + loginId));
    }

    @Override
    public Object rememberId(String loginId) {
        return rememberRepository.findById(loginId).orElse(null);
    }

    @Override
    public void lettersave(LetterEntity lentity) {
        letterRepository.save(lentity);
    }

    @Override
    public List<LetterEntity> letter(String loginId) {
        return letterRepository.findByUserid(loginId);
    }

    @Override
    public LetterEntity letterdetail(long letternum) {
        return letterRepository.findById(letternum).orElse(null);
    }

    @Override
    public void petsave(RememberEntity rent) {
        rememberRepository.save(rent);
    }


}