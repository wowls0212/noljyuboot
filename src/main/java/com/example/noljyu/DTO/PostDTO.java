package com.example.noljyu.DTO;

import com.example.noljyu.Entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO {

    long postnum;
    String id, posttitle, postdetail, postimg2;
    int postcnt;
    LocalDate postdate;
    String posttype;
    int good;

    public PostEntity psave() {
        return PostEntity.builder()
                .postnum(postnum)
                .id(id)
                .posttitle(posttitle)
                .postdetail(postdetail)
                .postimg(postimg2)
                .postcnt(postcnt)
                .postdate(postdate)
                .posttype(posttype)
                .good(good)
                .build();
    }

    public void setPostimg(String howimg) {
    }
}
