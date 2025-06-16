package com.example.noljyu.Repository;

import com.example.noljyu.Entity.RememberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RememberRepository extends JpaRepository<RememberEntity, String> {

}
