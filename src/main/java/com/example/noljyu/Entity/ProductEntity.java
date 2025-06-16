package com.example.noljyu.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "\"product\"")
@Builder
@SequenceGenerator(
        name = "product",
        sequenceName = "productnum_seq",
        allocationSize = 1,
        initialValue = 1
)
public class ProductEntity {
    @Id
    @Column(name = "\"productnum\"")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product")
    long productnum;
    @Column(name = "\"id\"", nullable = false)
    @NotNull(message = "id는 필수값입니다.")
    String id;
    @Column(name = "\"animal\"")
    String animal;
    @Column(name = "\"productlist\"")
    String productlist;
    @Column(name = "\"price\"")
    int price;
    @Column(name = "\"productimg\"")
    String productimg;
    @Column(name = "\"productcnt\"")
    int productcnt;
    @Column(name = "\"productname\"")
    String productname;
    @Column(name = "\"productlink\"")
    String productlink;

    public int getProductcnt() {
        return productcnt;
    }

    public void setProductcnt(int productcnt) {
        this.productcnt = productcnt;
    }
}
