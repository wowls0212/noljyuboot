package com.example.noljyu.Service;

import com.example.noljyu.DTO.ProductDTO;
import com.example.noljyu.Entity.ProductEntity;
import com.example.noljyu.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void productinput(ProductEntity pentity) {
        productRepository.save(pentity);
    }

    @Override
    public void productupdate(ProductEntity pentity) {
        productRepository.save(pentity);
    }

    @Override
    public List<ProductEntity> productout() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductEntity> producttotal() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity productdelete(long productnum) {
        ProductEntity pentity = productRepository.findById(productnum).orElse(null);
        if (pentity != null) {
            productRepository.deleteById(productnum);
        }
        return pentity;
    }

    @Override
    public ProductEntity getProductDetail(long productnum) {
        return productRepository.findById(productnum).orElse(null);
    }

    @Override
    public List<ProductDTO> psearch2(String name, String val, String value) {
        List<ProductEntity> entities;
        if ("dog".equalsIgnoreCase(name)) {
            entities = productRepository.findByAnimalAndProductlistAndProductnameContaining(name, val, value);
        }
        else if ("cat".equalsIgnoreCase(name)) {
            entities = productRepository.findByAnimalAndProductlistAndProductnameContaining(name, val, value);
        }
        else if ("bird".equalsIgnoreCase(name)) {
            entities = productRepository.findByAnimalAndProductlistAndProductnameContaining(name, val, value);
        }
        else if ("fish".equalsIgnoreCase(name)) {
            entities = productRepository.findByAnimalAndProductlistAndProductnameContaining(name, val, value);
        }
        else if ("creep".equalsIgnoreCase(name)) {
            entities = productRepository.findByAnimalAndProductlistAndProductnameContaining(name, val, value);
        }
        else {
            entities = productRepository.findByProductnameContaining(value);
        }
        return entities.stream()
                .map(entity -> new ProductDTO(entity))  // ProductDTO에 Entity 기반 생성자 있다고 가정
                .toList();
    }

    @Override
    public List<ProductEntity> dogfood() {
        return productRepository.findByAnimalAndProductlist("dog","foodsnack");
    }

    @Override
    public List<ProductEntity> dogtoy() {
        return productRepository.findByAnimalAndProductlist("dog","toyliving");
    }

    @Override
    public List<ProductEntity> doghealth() {
        return productRepository.findByAnimalAndProductlist("dog","healthtoilet");
    }

    @Override
    public List<ProductEntity> dogbeauty() {
        return productRepository.findByAnimalAndProductlist("dog","beautyfashion");
    }

    @Override
    public List<ProductEntity> catfood() {
        return productRepository.findByAnimalAndProductlist("cat","foodsnack");
    }

    @Override
    public List<ProductEntity> cattoy() {
        return productRepository.findByAnimalAndProductlist("cat","toyliving");
    }

    @Override
    public List<ProductEntity> cathealth() {
        return productRepository.findByAnimalAndProductlist("cat","healthtoilet");
    }

    @Override
    public List<ProductEntity> catbeauty() {
        return productRepository.findByAnimalAndProductlist("cat","beautyfashion");
    }

    @Override
    public List<ProductEntity> birdfood() {
        return productRepository.findByAnimalAndProductlist("bird","foodsnack");
    }

    @Override
    public List<ProductEntity> birdtoy() {
        return productRepository.findByAnimalAndProductlist("bird","toyliving");
    }

    @Override
    public List<ProductEntity> birdhealth() {
        return productRepository.findByAnimalAndProductlist("bird","healthtoilet");
    }

    @Override
    public List<ProductEntity> birdbeauty() {
        return productRepository.findByAnimalAndProductlist("bird","beautyfashion");
    }

    @Override
    public List<ProductEntity> fishfood() {
        return productRepository.findByAnimalAndProductlist("fish","foodsnack");
    }

    @Override
    public List<ProductEntity> fishtoy() {
        return productRepository.findByAnimalAndProductlist("fish","toyliving");
    }

    @Override
    public List<ProductEntity> fishhealth() {
        return productRepository.findByAnimalAndProductlist("fish","healthtoilet");
    }

    @Override
    public List<ProductEntity> fishbeauty() {
        return productRepository.findByAnimalAndProductlist("fish","beautyfashion");
    }

    @Override
    public List<ProductEntity> creepfood() {
        return productRepository.findByAnimalAndProductlist("creep","foodsnack");
    }

    @Override
    public List<ProductEntity> creeptoy() {
        return productRepository.findByAnimalAndProductlist("creep","toyliving");
    }

    @Override
    public List<ProductEntity> creephealth() {
        return productRepository.findByAnimalAndProductlist("creep","healthtoilet");
    }

    @Override
    public List<ProductEntity> creepbeauty() {
        return productRepository.findByAnimalAndProductlist("creep","beautyfashion");
    }

    @Override
    public void productcnt(Long productnum) {
        ProductEntity pentity = productRepository.findById(productnum).orElse(null);
        if (pentity != null) {
            int currentCount = pentity.getProductcnt(); // 예: 조회수 필드명
            pentity.setProductcnt(currentCount + 1);
            productRepository.save(pentity);
        }
    }

    @Override
    public Page<ProductEntity> pagelist(int page) {
        return productRepository.findAll(PageRequest.of(page,5));
    }


}