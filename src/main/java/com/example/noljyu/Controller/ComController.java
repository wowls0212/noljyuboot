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
public class ComController {

    @Autowired
    PostService postService;
    String path="C:\\MBC12AI\\springboot\\noljyu\\src\\main\\resources\\static\\image";

    @GetMapping(value = "cominput")
    public String cinput(){

        return "com/cominput";
    }
    @PostMapping(value = "comsave")
    public String csave(PostDTO dto, MultipartHttpServletRequest request) throws IOException {
        MultipartFile mf = request.getFile("comimg");
        String comimg = mf.getOriginalFilename();
        UUID uu = UUID.randomUUID();
        comimg = uu + "_" + comimg;
        mf.transferTo(new File(path + "//" + comimg));
        dto.setPostimg2(comimg);
        dto.setPosttype("community");
        PostEntity pentity=dto.psave();
        postService.csave(pentity);
        return "redirect:/comout";
    }
    @GetMapping(value = "comout")
    public String hout(Model model){
        List<PostEntity> list = postService.cout();
        model.addAttribute("list",list);
        return "com/comout";
    }
    @GetMapping(value = "comdetail")
    public String cdetail(@RequestParam("postnum") long postnum, Model model){
        PostEntity dto = postService.cdetail(postnum);
        postService.ccnt(postnum);
        model.addAttribute("dto",dto);
        return "com/comdetail";
    }
    @GetMapping(value = "comupdate")
    public String cupdate(@RequestParam("comnum") long postnum, Model model){
        PostEntity dto = postService.cdetail(postnum);
        model.addAttribute("dto",dto);
        return "com/comupdate";
    }
    @PostMapping(value = "comupdatesave")
    public String cupdatesave(MultipartHttpServletRequest request, PostDTO dto) throws IOException {
        MultipartFile mf = request.getFile("comimg");
        String comimg = mf.getOriginalFilename();
        UUID uu = UUID.randomUUID();
        comimg = uu+"_"+comimg;
        mf.transferTo(new File(path+"//"+comimg));
        String lastimg = request.getParameter("lastimg");
        File ff = new File(path+"//"+lastimg);
        ff.delete();
        PostEntity pentity=dto.psave();
        postService.csave(pentity);
        return "redirect:/comout";
    }
}
