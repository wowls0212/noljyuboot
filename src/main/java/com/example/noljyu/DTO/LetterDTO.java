package com.example.noljyu.DTO;

import com.example.noljyu.Entity.LetterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LetterDTO {
    long letternum;
    String id;
    String lettertitle;
    String letterdetail;
    LocalDate writeday;

    public LetterEntity lettersave() {
        return LetterEntity.builder()
                .letternum(letternum)
                .id(id)
                .lettertitle(lettertitle)
                .letterdetail(letterdetail)
                .writeday(writeday)
                .build();
    }
}
