package com.example.noljyu.DTO;

import com.example.noljyu.Entity.NoticeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoticeDTO {
    long nnum;
    String ntitle,ndetail;
    int ncnt;

    public NoticeEntity nsave() {
        return NoticeEntity.builder()
                .nnum(nnum)
                .ntitle(ntitle)
                .ndetail(ndetail)
                .ncnt(ncnt)
                .build();
    }
}
