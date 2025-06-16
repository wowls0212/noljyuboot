package com.example.noljyu.Repository;

import com.example.noljyu.Entity.WalkWriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkWriteRepository extends JpaRepository<WalkWriteEntity, Long> {
}
