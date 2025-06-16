package com.example.noljyu.DTO;

import com.example.noljyu.Entity.RememberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RememberDTO {
    private String id;
    private String petname;
    private String petimg2;
    private LocalDate deadday;

    public RememberEntity petsave() {
        return RememberEntity.builder()
                .id(id)
                .petname(petname)
                .petimg(petimg2)
                .deadday(deadday)
                .build();
    }
}
