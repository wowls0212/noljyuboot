package com.example.noljyu.Repository;

import com.example.noljyu.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = """
      SELECT *
      FROM (
        SELECT pe1_0.*, ROWNUM rnum
        FROM (
            SELECT productnum, animal, id, price, productcnt, productimg, productlink, productlist, productname
            FROM product0602
            ORDER BY id DESC
        ) pe1_0
        WHERE ROWNUM <= :endRow
      )
      WHERE rnum > :startRow
      """, nativeQuery = true)
    List<ProductEntity> findPage(@Param("startRow") int startRow, @Param("endRow") int endRow);

    @Query(value = "SELECT COUNT(*) FROM product0602", nativeQuery = true)
    int countAll();

    Optional<ProductEntity> findByProductnum(int num);

    List<ProductEntity> findByAnimalAndProductlistAndProductnameContaining(String animal, String productlist, String productname);

    List<ProductEntity> findByProductnameContaining(String productname);

    List<ProductEntity> findByAnimal(String animal);

    List<ProductEntity> findByAnimalAndProductnameContaining(String animal, String keyword);

    List<ProductEntity> findByAnimalAndProductlist(String dog, String productlist);
}