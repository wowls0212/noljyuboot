package com.example.noljyu.Repository;

import com.example.noljyu.Entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity,Long> {
    @Transactional
    @Query(value = "select * from \"notice\" where \"nnum\"=(select max(\"nnum\") from \"notice\")",nativeQuery = true)
    NoticeEntity nout();

}
