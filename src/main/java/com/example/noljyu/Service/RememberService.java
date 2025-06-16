package com.example.noljyu.Service;

import com.example.noljyu.Entity.LetterEntity;
import com.example.noljyu.Entity.RememberEntity;

import java.time.LocalDate;
import java.util.List;

public interface RememberService {

    List<RememberEntity> findAll();

    RememberEntity getLetterDetail(String loginId);

    Object rememberId(String loginId);

    void lettersave(LetterEntity lentity);

    List<LetterEntity> letter(String loginId);

    LetterEntity letterdetail(long letternum);

    void petsave(RememberEntity rent);
}

