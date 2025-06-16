package com.example.noljyu.Repository;

import com.example.noljyu.DTO.WalkDTO;
import com.example.noljyu.Entity.WalkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalkRepository extends JpaRepository<WalkEntity, Long> {

    // 기본 검색 - 제목 포함
    List<WalkEntity> findByWalktitleContaining(String keyword);

    // 기본 검색 - 상세 포함
    List<WalkEntity> findByWalkdetailContaining(String keyword);

    // DTO 생성자에 맞춘 커스텀 JPQL 검색 (제목 또는 상세)
    @Query("SELECT new com.example.noljyu.DTO.WalkDTO(w.walknum, w.id, w.walktitle, w.walkdetail, w.walkimg, w.walkcnt) " +
            "FROM WalkEntity w " +
            "WHERE w.walktitle LIKE %:keyword% OR w.walkdetail LIKE %:keyword%")
    List<WalkDTO> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT new com.example.noljyu.DTO.WalkDTO(w.walknum, w.id, w.walktitle, w.walkdetail, w.walkimg, w.walkcnt) " +
            "FROM WalkEntity w WHERE w.walktitle LIKE %:walkvalue%")
    List<WalkDTO> searchByWalktitle(@Param("walkvalue") String walkvalue);

    @Query("SELECT new com.example.noljyu.DTO.WalkDTO(w.walknum, w.id, w.walktitle, w.walkdetail, w.walkimg, w.walkcnt) " +
            "FROM WalkEntity w WHERE w.walkdetail LIKE %:walkvalue%")
    List<WalkDTO> searchByWalkdetail(@Param("walkvalue") String walkvalue);
}
