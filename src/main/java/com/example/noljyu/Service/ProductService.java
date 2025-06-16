package com.example.noljyu.Service;

import com.example.noljyu.DTO.ProductDTO;
import com.example.noljyu.Entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    void productinput(ProductEntity pentity);

    void productupdate(ProductEntity pentity);

    List<ProductEntity> productout();

    List<ProductEntity> producttotal();

    ProductEntity productdelete(long productnum);

    ProductEntity getProductDetail(long productnum);

    List<ProductDTO> psearch2(String name, String val, String value);

    List<ProductEntity> dogfood();

    List<ProductEntity> dogtoy();

    List<ProductEntity> doghealth();

    List<ProductEntity> dogbeauty();

    List<ProductEntity> catfood();

    List<ProductEntity> cattoy();

    List<ProductEntity> cathealth();

    List<ProductEntity> catbeauty();

    List<ProductEntity> birdfood();

    List<ProductEntity> birdtoy();

    List<ProductEntity> birdhealth();

    List<ProductEntity> birdbeauty();

    List<ProductEntity> fishfood();

    List<ProductEntity> fishtoy();

    List<ProductEntity> fishhealth();

    List<ProductEntity> fishbeauty();

    List<ProductEntity> creepfood();

    List<ProductEntity> creeptoy();

    List<ProductEntity> creephealth();

    List<ProductEntity> creepbeauty();

    void productcnt(Long productnum);

    Page<ProductEntity> pagelist(int page);
}
