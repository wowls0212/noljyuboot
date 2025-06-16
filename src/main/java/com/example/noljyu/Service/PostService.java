package com.example.noljyu.Service;

import com.example.noljyu.Entity.PostEntity;
import com.example.noljyu.Entity.PostReviewEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    void hsave(PostEntity pentity);

    List<PostEntity> hout();

    void fsave(PostEntity pentity);

    List<PostEntity> fout();

    void csave(PostEntity pentity);

    List<PostEntity> cout();

    PostEntity hdetail(long postnum);

    PostEntity cdetail(long postnum);

    PostEntity fdetail(long postnum);

    void hcnt(long postnum);

    void fcnt(long postnum);

    void ccnt(long postnum);

    void gcnt(long postnum);

    void hdel(long hownum);

    PostEntity postreport(long postnum);

    List<PostReviewEntity> postreview(long postnum);

    PostEntity hbest();

    PostEntity fbest();

    PostEntity cbest();

    void prsave(PostReviewEntity prentity);
}