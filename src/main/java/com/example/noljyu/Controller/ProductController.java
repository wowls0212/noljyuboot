package com.example.noljyu.Controller;

import com.example.noljyu.DTO.ProductDTO;
import com.example.noljyu.Entity.ProductEntity;
import com.example.noljyu.Repository.ProductRepository;
import com.example.noljyu.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    String path="C:\\MBC12AI\\springboot\\noljyu\\src\\main\\resources\\static\\image";

    @GetMapping(value = "/productinput")
    public String productinput() {

        return "product/productinput";
    }

    @PostMapping(value = "/productsave")
    public String productsave(ProductDTO dto, MultipartHttpServletRequest mul) throws IOException {
        MultipartFile mf = mul.getFile("productimg");

        String fname = ""; // 기본 파일명 초기화
        if (mf != null && !mf.isEmpty()) {
            fname = mf.getOriginalFilename();
            UUID uu=UUID.randomUUID();
            fname=uu.toString()+"_"+fname;
            mf.transferTo(new File(path + "\\" + fname));
            dto.setProductimgName(fname); // DTO에 이미지명 설정
        }

        ProductEntity entity = dto.toentity(fname);
        productService.productinput(entity);

        return "redirect:/";
    }

    @GetMapping("/productout")
    public String productout(Model model,
                              @RequestParam(value = "keyword", required = false) String keyword) {
        List<ProductEntity> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = productRepository.findByProductnameContaining(keyword);
        } else {
            list = productService.productout(); // 전체 출력
        }
        model.addAttribute("list", list);
        return "product/productout";
    }

    @GetMapping(value = "/producttotal")
    public String producttotal(HttpServletRequest request,Model model)
    {
        List<ProductEntity> list = productService.producttotal();
        model.addAttribute("list", list);

        return "product/producttotal";
    }

    @GetMapping("/productdetail")
    public String productDetail(@RequestParam("num") Long productnum, Model model) {
        ProductEntity dto = productService.getProductDetail(productnum);
        productService.productcnt(productnum);
        model.addAttribute("dto", dto);
        return "product/productdetail";
    }

    @GetMapping("/pmodify1")
    public String pmodify1(@RequestParam("num") Long productnum, Model model) {
        ProductEntity dto = productService.getProductDetail(productnum);
        model.addAttribute("dto", dto);
        return "product/pmodify1";
    }

    @PostMapping(value = "/pmodifysave")
    public String pmodifysave(ProductDTO dto, MultipartHttpServletRequest mul) throws IOException {
        MultipartFile mf = mul.getFile("productimg");

        String fname = dto.getProductimgName();
        if (mf != null && !mf.isEmpty()) {
            // 기존 파일 삭제
            File oldFile = new File(path + "\\" + fname);
            if (oldFile.exists()) {
                oldFile.delete();
            }
            // 새 파일 저장
            String originalName = mf.getOriginalFilename();
            UUID uu = UUID.randomUUID();
            fname = uu.toString() + "_" + originalName;
            mf.transferTo(new File(path + "\\" + fname));
        }
        dto.setProductimgName(fname);

        ProductEntity entity = dto.toentity(fname);
        productService.productupdate(entity);

        return "redirect:/producttotal";
    }

    @GetMapping(value = "/pdelete1")
    public String pdelete1(@RequestParam("num") Long productnum, Model model) {
        ProductEntity pentity=productService.productdelete(productnum);
        // 이미지 경로 지정
        String fname = pentity.getProductimg();
        File ff = new File(path + "\\" + fname);
        // 파일이 존재하면 삭제
        if (ff.exists()) {
            ff.delete();
        }

        return "redirect:/producttotal";
    }

    @GetMapping(value = "/productsearch")
    public String productsearch()
    {
        return "product/psearch1";
    }

    @RequestMapping(value = "/psearch2")
    public String psearch2(HttpServletRequest request, Model model) {
        String name = request.getParameter("scname");
        String val = request.getParameter("scval");
        String value = request.getParameter("scvalue");

        // 검색 결과 가져오기
        List<ProductDTO> list = productService.psearch2(name, val, value);
        model.addAttribute("list", list);

        return "product/psearch2";
    }
    @GetMapping("/dogproductmain")
    public String dogproductmain1(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<ProductEntity> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = productRepository.findByAnimalAndProductnameContaining("dog", keyword);
        } else {
            list = productRepository.findByAnimal("dog");
        }
        model.addAttribute("list", list);

        return "product/dogproductmain";
    }

    @GetMapping(value = "dogfoodsnack")
    public String dogfoodsnack(Model model){
        List<ProductEntity> list=productService.dogfood();

        model.addAttribute("list", list);

        return "product/dogproduct/dogfoodsnack";
    }

    @GetMapping(value = "dogtoyliving")
    public String dogtoyliving(Model model){
        List<ProductEntity> list=productService.dogtoy();

        model.addAttribute("list", list);

        return "product/dogproduct/dogtoyliving";
    }

    @GetMapping(value = "doghealthtoilet")
    public String doghealthtoilet(Model model){
        List<ProductEntity> list=productService.doghealth();

        model.addAttribute("list", list);

        return "product/dogproduct/doghealthtoilet";
    }

    @GetMapping(value = "dogbeautyfashion")
    public String dogbeautyfashion(Model model){
        List<ProductEntity> list=productService.dogbeauty();

        model.addAttribute("list", list);

        return "product/dogproduct/dogbeautyfashion";
    }

    @GetMapping("/catproductmain")
    public String catproductmain(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<ProductEntity> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = productRepository.findByAnimalAndProductnameContaining("cat", keyword);
        } else {
            list = productRepository.findByAnimal("cat");
        }
        model.addAttribute("list", list);

        return "product/catproductmain";
    }

    @GetMapping(value = "catfoodsnack")
    public String catfoodsnack(Model mo){
        List<ProductEntity> list=productService.catfood();

        mo.addAttribute("list", list);

        return "product/catproduct/catfoodsnack";
    }

    @GetMapping(value = "cattoyliving")
    public String cattoyliving(Model mo){
        List<ProductEntity> list=productService.cattoy();

        mo.addAttribute("list", list);

        return "product/catproduct/cattoyliving";
    }

    @GetMapping(value = "cathealthtoilet")
    public String cathealthtoilet(Model mo){
        List<ProductEntity> list=productService.cathealth();

        mo.addAttribute("list", list);

        return "product/catproduct/cathealthtoilet";
    }

    @GetMapping(value = "catbeautyfashion")
    public String catbeautyfashion(Model mo){
        List<ProductEntity> list=productService.catbeauty();

        mo.addAttribute("list", list);

        return "product/catproduct/catbeautyfashion";
    }

    @GetMapping("/birdproductmain")
    public String birdproductmain(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<ProductEntity> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = productRepository.findByAnimalAndProductnameContaining("bird", keyword);
        } else {
            list = productRepository.findByAnimal("bird");
        }
        model.addAttribute("list", list);

        return "product/birdproductmain";
    }

    @GetMapping(value = "birdfoodsnack")
    public String birdfoodsnack(Model mo){
        List<ProductEntity> list=productService.birdfood();

        mo.addAttribute("list", list);

        return "product/birdproduct/birdfoodsnack";
    }

    @GetMapping(value = "birdtoyliving")
    public String birdtoyliving(Model mo){
        List<ProductEntity> list=productService.birdtoy();

        mo.addAttribute("list", list);

        return "product/birdproduct/birdtoyliving";
    }

    @GetMapping(value = "birdhealthtoilet")
    public String birdhealthtoilet(Model mo){
        List<ProductEntity> list=productService.birdhealth();

        mo.addAttribute("list", list);

        return "product/birdproduct/birdhealthtoilet";
    }

    @GetMapping(value = "birdbeautyfashion")
    public String birdbeautyfashion(Model mo){
        List<ProductEntity> list=productService.birdbeauty();

        mo.addAttribute("list", list);

        return "product/birdproduct/birdbeautyfashion";
    }

    @GetMapping("/fishproductmain")
    public String fishproductmain(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<ProductEntity> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = productRepository.findByAnimalAndProductnameContaining("fish", keyword);
        } else {
            list = productRepository.findByAnimal("fish");
        }
        model.addAttribute("list", list);

        return "product/fishproductmain";
    }

    @GetMapping(value = "fishfoodsnack")
    public String fishfoodsnack(Model mo){
        List<ProductEntity> list=productService.fishfood();

        mo.addAttribute("list", list);

        return "product/fishproduct/fishfoodsnack";
    }

    @GetMapping(value = "fishtoyliving")
    public String fishtoyliving(Model mo){
        List<ProductEntity> list=productService.fishtoy();

        mo.addAttribute("list", list);

        return "product/fishproduct/fishtoyliving";
    }

    @GetMapping(value = "fishhealthtoilet")
    public String fishhealthtoilet(Model mo){
        List<ProductEntity> list=productService.fishhealth();

        mo.addAttribute("list", list);

        return "product/fishproduct/fishhealthtoilet";
    }

    @GetMapping(value = "fishbeautyfashion")
    public String fishbeautyfashion(Model mo){
        List<ProductEntity> list=productService.fishbeauty();

        mo.addAttribute("list", list);

        return "product/fishproduct/fishbeautyfashion";
    }

    @GetMapping("/creepproductmain")
    public String creepproductmain(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<ProductEntity> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = productRepository.findByAnimalAndProductnameContaining("creep", keyword);
        } else {
            list = productRepository.findByAnimal("creep");
        }
        model.addAttribute("list", list);

        return "product/creepproductmain";
    }

    @GetMapping(value = "creepfoodsnack")
    public String creepfoodsnack(Model mo){
        List<ProductEntity> list=productService.creepfood();

        mo.addAttribute("list", list);

        return "product/creepproduct/creepfoodsnack";
    }

    @GetMapping(value = "creeptoyliving")
    public String creeptoyliving(Model mo){
        List<ProductEntity> list=productService.creeptoy();

        mo.addAttribute("list", list);

        return "product/creepproduct/creeptoyliving";
    }

    @GetMapping(value = "creephealthtoilet")
    public String creephealthtoilet(Model mo){
        List<ProductEntity> list=productService.creephealth();

        mo.addAttribute("list", list);

        return "product/creepproduct/creephealthtoilet";
    }

    @GetMapping(value = "creepbeautyfashion")
    public String creepbeautyfashion(Model mo){
        List<ProductEntity> list=productService.creepbeauty();

        mo.addAttribute("list", list);

        return "product/creepproduct/creepbeautyfashion";
    }

}
