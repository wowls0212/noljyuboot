package com.example.noljyu.Repository;

import com.example.noljyu.Entity.LetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Repository
public interface LetterRepository extends JpaRepository<LetterEntity, Long> {
    @Transactional
    @Query(value = "select * from \"letter\" where \"id\" = ?",nativeQuery = true)
    List<LetterEntity> findByUserid(String loginId);
}
