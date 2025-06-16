package com.example.noljyu.Service;

import com.example.noljyu.Entity.PostReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReviewRepository extends JpaRepository<PostReviewEntity, Long> {
}
