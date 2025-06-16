package com.example.noljyu.DTO;

import com.example.noljyu.Entity.PostReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostReviewDTO {
    long postreviewnum;
    String id;
    long postnum;
    String postreview;
    int postgroups, poststep,postindent;
    String posttype;

    public PostReviewEntity prsave() {
        return PostReviewEntity.builder()
                .postreviewnum(postreviewnum)
                .postgroups(0)
                .poststep(0)
                .postindent(0)
                .id(id)
                .postnum(postnum)
                .postreview(postreview)
                .posttype(posttype)
                .build();
    }
}
