package com.example.noljyu.Repository;

import com.example.noljyu.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query(value = "SELECT COUNT(*) FROM \"noluser\" WHERE LOWER(\"id\") = LOWER(?1)", nativeQuery = true)
    Integer idcnt(String id);

    @Query(value = "SELECT COUNT(*) FROM \"noluser\" WHERE LOWER(\"nickname\") = LOWER(?1)", nativeQuery = true)
    Integer nickcnt(String nickname);


    @Query(value = "SELECT \"admin_pass\" FROM \"AdminPassword\" WHERE ROWNUM = 1", nativeQuery = true)
    String adpass();

    @Query(value = "SELECT COUNT(*) FROM \"noluser\"", nativeQuery = true)
    int usertotal();

    @Query(value = "SELECT COUNT(*) FROM \"noluser\"", nativeQuery = true)
    int usersearchtotal();

    UserEntity findOneById(String id);

    List<UserEntity> findByIdContaining(String keyword);
    List<UserEntity> findByNameContaining(String keyword);
    List<UserEntity> findByNicknameContaining(String keyword);
    List<UserEntity> findByAddressContaining(String keyword);
    List<UserEntity> findByMyanimalContaining(String keyword);

    @Query("SELECT u FROM UserEntity u WHERE u.id = :id AND u.petname = :petname")
    UserEntity findByIdAndPetname(String id, String petname);
}
