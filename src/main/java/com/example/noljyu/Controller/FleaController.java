package com.example.noljyu.Controller;

import com.example.noljyu.DTO.PostDTO;
import com.example.noljyu.Entity.PostEntity;
import com.example.noljyu.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class FleaController {

    @Autowired
    PostService postService;
    String path="C:\\MBC12AI\\springboot\\noljyu\\src\\main\\resources\\static\\image";

    @GetMapping(value = "fleainput")
    public String finput() {
        return "flea/fleainput";
    }
    @PostMapping(value = "fleasave")
    public String hsave(PostDTO dto, MultipartHttpServletRequest request) throws IOException {
        MultipartFile mf = request.getFile("fleaimg");
        String fleaimg = mf.getOriginalFilename();
        UUID uu = UUID.randomUUID();
        fleaimg = uu+"_"+fleaimg;
        mf.transferTo(new File(path+"//"+fleaimg));
        dto.setPostimg2(fleaimg);
        dto.setPosttype("flea");
        PostEntity pentity=dto.psave();
        postService.fsave(pentity);
        return "redirect:/fleaout";
    }
    @GetMapping(value = "fleaout")
    public String hout(Model model){
        List<PostEntity> list = postService.fout();
        model.addAttribute("list",list);
        return "flea/fleaout";
    }
    @GetMapping(value = "fleadetail")
    public String fdetail(@RequestParam("postnum") long postnum, Model model){
        PostEntity dto = postService.fdetail(postnum);
        postService.fcnt(postnum);
        model.addAttribute("dto",dto);
        return "flea/fleadetail";
    }
    @GetMapping(value = "fleaupdate")
    public String fupdate(@RequestParam("fleanum") long postnum, Model model){
        PostEntity dto = postService.fdetail(postnum);
        model.addAttribute("dto",dto);
        return "flea/fleaupdate";
    }
    @PostMapping(value = "fleaupdatesave")
    public String fupdatesave(MultipartHttpServletRequest request, PostDTO dto) throws IOException {
        MultipartFile mf = request.getFile("fleaimg");
        String fleaimg = mf.getOriginalFilename();
        UUID uu = UUID.randomUUID();
        fleaimg = uu+"_"+fleaimg;
        mf.transferTo(new File(path+"//"+fleaimg));
        String lastimg = request.getParameter("lastimg");
        File ff = new File(path+"//"+lastimg);
        ff.delete();
        PostEntity pentity=dto.psave();
        postService.fsave(pentity);
        return "redirect:/flea/fleaout";
    }
}
