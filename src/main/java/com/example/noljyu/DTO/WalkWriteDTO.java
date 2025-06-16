package com.example.noljyu.DTO;

import com.example.noljyu.Entity.WalkWriteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WalkWriteDTO {
    long walkwritenum;
    String id, walkwritetitle, walkwritedetail;

    public WalkWriteEntity walkwrite() {
        return WalkWriteEntity.builder()
                .walkwritenum(walkwritenum)
                .id(id)
                .walkwritetitle(walkwritetitle)
                .walkwritedetail(walkwritedetail)
                .build();
    }
}
