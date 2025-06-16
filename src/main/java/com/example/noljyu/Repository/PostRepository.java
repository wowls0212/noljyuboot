package com.example.noljyu.Repository;

import com.example.noljyu.Entity.PostEntity;
import com.example.noljyu.Entity.PostReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Transactional
    @Query(value = "SELECT * FROM \"post\" WHERE \"posttype\" = 'knowhow'", nativeQuery = true)
    List<PostEntity> findByPosttype(@Param("posttype") String posttype);

    @Transactional
    @Query(value = "SELECT * FROM \"post\" WHERE \"posttype\" = 'flea'", nativeQuery = true)
    List<PostEntity> findByflea(String flea);

    @Transactional
    @Query(value = "SELECT * FROM \"post\" WHERE \"posttype\" = 'community'", nativeQuery = true)
    List<PostEntity> findBycom(String com);

    @Transactional
    @Modifying
    @Query(value = "UPDATE \"post\" SET \"postcnt\" = \"postcnt\" + 1 WHERE \"postnum\" = :postnum", nativeQuery = true)
    void hcnt(long postnum);

    @Transactional
    @Modifying
    @Query(value = "UPDATE \"post\" SET \"postcnt\" = \"postcnt\" + 1 WHERE \"postnum\" = :postnum", nativeQuery = true)
    void ccnt(long postnum);

    @Transactional
    @Modifying
    @Query(value = "UPDATE \"post\" SET \"postcnt\" = \"postcnt\" + 1 WHERE \"postnum\" = :postnum", nativeQuery = true)
    void fcnt(long postnum);

    @Transactional
    @Modifying
    @Query(value = "UPDATE \"post\" SET \"good\" = \"good\" + 1 WHERE \"postnum\" = :postnum", nativeQuery = true)
    void gcnt(long postnum);

    @Transactional
    @Query(value = "SELECT * FROM \"postreview\" WHERE \"postnum\" = :postnum", nativeQuery = true)
    List<PostReviewEntity> findByreview(long postnum);

    @Transactional
    @Query(value = "SELECT * FROM (SELECT * FROM \"post\" WHERE \"posttype\" = 'knowhow' ORDER BY \"good\" DESC) WHERE rownum = 1", nativeQuery = true)
    PostEntity hbest();

    @Transactional
    @Query(value = "SELECT * FROM (SELECT * FROM \"post\" WHERE \"posttype\" = 'flea' ORDER BY \"good\" DESC) WHERE rownum = 1", nativeQuery = true)
    PostEntity fbest();

    @Transactional
    @Query(value = "SELECT * FROM (SELECT * FROM \"post\" WHERE \"posttype\" = 'community' ORDER BY \"good\" DESC) WHERE rownum = 1", nativeQuery = true)
    PostEntity cbest();


    @Query("SELECT p FROM PostEntity p WHERE p.id = :id ORDER BY p.postdate DESC")
    List<PostEntity> findByIdOrderByPostdateDesc(@Param("id") String id);

    @Query("SELECT COALESCE(SUM(p.good), 0) FROM PostEntity p WHERE p.id = :id")
    int sumGoodById(@Param("id") String id);


}
