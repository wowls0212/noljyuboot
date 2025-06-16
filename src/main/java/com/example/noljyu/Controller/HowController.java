package com.example.noljyu.Controller;

import com.example.noljyu.DTO.PostDTO;
import com.example.noljyu.DTO.PostReviewDTO;
import com.example.noljyu.Entity.PostEntity;
import com.example.noljyu.Entity.PostReviewEntity;
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
public class HowController {

    @Autowired
    PostService postService;

    String path = "C:\\MBC12AI\\springboot\\noljyu\\src\\main\\resources\\static\\image";

    @GetMapping(value = "/howinput")
    public String hinput() {
        return "how/howinput";
    }

    @PostMapping(value = "/howsave")
    public String hsave(PostDTO dto, MultipartHttpServletRequest request) throws IOException {
        MultipartFile mf = request.getFile("howimg");
        String howimg = mf.getOriginalFilename();
        UUID uu = UUID.randomUUID();
        howimg = uu + "_" + howimg;
        mf.transferTo(new File(path + "//" + howimg));
        dto.setPostimg2(howimg);
        dto.setPosttype("knowhow");
        PostEntity pentity = dto.psave();
        postService.hsave(pentity);

        return "redirect:/howout";
    }

    @GetMapping(value = "/howout")
    public String hout(Model model) {
        List<PostEntity> list = postService.hout();
        model.addAttribute("list", list);
        return "how/howout";
    }

    // 🔧 변경된 부분: /howdetail 매핑 추가
    @GetMapping(value = "/howdetail")
    public String hdetail(@RequestParam("postnum") long postnum, Model model) {
        PostEntity dto = postService.hdetail(postnum);
        postService.hcnt(postnum); // 조회수 증가
        model.addAttribute("dto", dto);

        List<PostReviewEntity> list = postService.postreview(postnum);
        model.addAttribute("list", list);

        return "how/howdetail";
    }

    @GetMapping(value = "/howupdate")
    public String hupdate(@RequestParam("hownum") long postnum, Model model) {
        PostEntity dto = postService.hdetail(postnum);
        model.addAttribute("dto", dto);
        return "how/howupdate";
    }

    @PostMapping(value = "/howupdatesave")
    public String hupdatesave(MultipartHttpServletRequest request, PostDTO dto) throws IOException {
        MultipartFile mf = request.getFile("howimg");
        String howimg = mf.getOriginalFilename();
        UUID uu = UUID.randomUUID();
        howimg = uu + "_" + howimg;
        mf.transferTo(new File(path + "//" + howimg));
        dto.setPostimg(howimg);

        String lastimg = request.getParameter("lastimg");
        File ff = new File(path + "//" + lastimg);
        if (ff.exists()) {
            ff.delete();
        }

        PostEntity pentity = dto.psave();
        postService.hsave(pentity);

        return "redirect:/howout";
    }

    @GetMapping(value = "/good1")
    public String good(@RequestParam("goodnum") long postnum) {
        postService.gcnt(postnum);
        return "redirect:/howdetail?postnum=" + postnum;
    }

    @GetMapping(value = "/howdelete")
    public String howdel(@RequestParam("hownum") long postnum) {
        postService.hdel(postnum);
        return "redirect:/howout";
    }

    @GetMapping(value = "/postreport1")
    public String report(@RequestParam("postnum") long postnum, Model model) {
        PostEntity dto = postService.postreport(postnum);
        model.addAttribute("dto", dto);
        return "report/postreport";
    }
    @PostMapping(value = "/howreviewsave")
    public String hrsave(PostReviewDTO dto, @RequestParam("postnum") long postnum){
        PostReviewEntity prentity = dto.prsave();
        postService.prsave(prentity);
        return "redirect:/howdetail?postnum="+postnum;
    }
}
