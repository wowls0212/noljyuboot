package com.example.noljyu.Service;

import com.example.noljyu.Entity.PostEntity;
import com.example.noljyu.Entity.PostReviewEntity;
import com.example.noljyu.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostReviewRepository postReviewRepository;
    @Override
    public void hsave(PostEntity pentity) {
        postRepository.save(pentity);
    }

    @Override
    public List<PostEntity> hout() {
        return postRepository.findByPosttype("knowhow");
    }

    @Override
    public void fsave(PostEntity pentity) {
        postRepository.save(pentity);
    }

    @Override
    public List<PostEntity> fout() {
        return postRepository.findByflea("flea");
    }

    @Override
    public void csave(PostEntity pentity) {
        postRepository.save(pentity);
    }

    @Override
    public List<PostEntity> cout() {
        return postRepository.findBycom("com");
    }

    @Override
    public PostEntity hdetail(long postnum) {
        return postRepository.findById(postnum).orElse(null);
    }

    @Override
    public PostEntity cdetail(long postnum) {
        return postRepository.findById(postnum).orElse(null);
    }

    @Override
    public PostEntity fdetail(long postnum) {
        return postRepository.findById(postnum).orElse(null);
    }

    @Override
    public void hcnt(long postnum) {
        postRepository.hcnt(postnum);
    }

    @Override
    public void fcnt(long postnum) {
        postRepository.fcnt(postnum);
    }

    @Override
    public void ccnt(long postnum) {
        postRepository.ccnt(postnum);
    }

    @Override
    public void gcnt(long postnum) {
        postRepository.gcnt(postnum);
    }

    @Override
    public void hdel(long hownum) {
        postRepository.deleteById(hownum);
    }

    @Override
    public PostEntity postreport(long postnum) {
        return postRepository.findById(postnum).orElse(null);
    }

    @Override
    public List<PostReviewEntity> postreview(long postnum) {
        return postRepository.findByreview(postnum);
    }

    @Override
    public PostEntity hbest() {
        return postRepository.hbest();
    }

    @Override
    public PostEntity fbest() {
        return postRepository.fbest();
    }
    @Override
    public PostEntity cbest() {
        return postRepository.cbest();
    }

    @Override
    public void prsave(PostReviewEntity prentity) {

        postReviewRepository.save(prentity);
    }


}
