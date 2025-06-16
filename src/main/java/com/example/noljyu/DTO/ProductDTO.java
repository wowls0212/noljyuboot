package com.example.noljyu.DTO;

import com.example.noljyu.Entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
    private long productnum;
    private String id;
    private String animal;
    private String productlist;
    private int price;
    private MultipartFile productimg;    // 업로드 시 사용
    private String productimgName;       // 저장된 이미지명 (조회 시 사용)
    private int productcnt;
    private String productname;
    private String productlink;

    // Entity → DTO 변환 생성자 (MultipartFile 없음)
    public ProductDTO(ProductEntity entity) {
        this.productnum = entity.getProductnum();
        this.productname = entity.getProductname();
        this.productlist = entity.getProductlist();
        this.productlink = entity.getProductlink();
        this.price = entity.getPrice();
        this.productcnt = entity.getProductcnt();
        this.animal = entity.getAnimal();
        this.productimgName = entity.getProductimg();
    }

    // DTO → Entity 변환
    public ProductEntity toentity(String fname) {
        return ProductEntity.builder()
                .productnum(productnum)
                .id(id)
                .animal(animal)
                .productlist(productlist)
                .price(price)
                .productimg(fname)
                .productcnt(productcnt)
                .productname(productname)
                .productlink(productlink)
                .build();
    }


}