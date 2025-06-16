package com.example.noljyu.DTO;

import com.example.noljyu.Entity.WalkEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WalkDTO {
    long walknum;
    String id;
    String walktitle;
    String walkdetail;
    MultipartFile walkimg;  // 업로드용, 조회용이 아님
    String walkimgName;     // DB 저장 이미지 파일명
    int walkcnt;

    // Entity → DTO 변환 생성자 (MultipartFile 없음)
    public WalkDTO(WalkEntity entity) {
        this.walknum = entity.getWalknum();
        this.id = entity.getId();
        this.walktitle = entity.getWalktitle();
        this.walkdetail = entity.getWalkdetail();
        this.walkimgName = entity.getWalkimg();
        this.walkcnt = entity.getWalkcnt();
    }

    // JPQL 쿼리에서 new 생성자로 쓸 생성자 (MultipartFile 제외)
    public WalkDTO(long walknum, String id, String walktitle, String walkdetail,
                   String walkimgName, int walkcnt) {
        this.walknum = walknum;
        this.id = id;
        this.walktitle = walktitle;
        this.walkdetail = walkdetail;
        this.walkimgName = walkimgName;
        this.walkcnt = walkcnt;
    }

    // DTO → Entity 변환
    public WalkEntity toentity(String fname) {
        return WalkEntity.builder()
                .walknum(walknum)
                .id(id)
                .walktitle(walktitle)
                .walkdetail(walkdetail)
                .walkimg(fname)
                .walkcnt(walkcnt)
                .build();
    }
}
