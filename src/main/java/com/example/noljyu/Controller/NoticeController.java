package com.example.noljyu.Controller;

import com.example.noljyu.DTO.NoticeDTO;
import com.example.noljyu.Entity.NoticeEntity;
import com.example.noljyu.Service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @GetMapping(value = "noticewrite")
    public String noticewrite(){
        return "notice/noticewrite";
    }
    @PostMapping(value = "noticesave")
    public String nsave(NoticeDTO dto){
        NoticeEntity net = dto.nsave();
        noticeService.nsave(net);
        return "redirect:/";
    }
    @GetMapping(value = "noticedetail")
    public String ndetail(Model model){
        NoticeEntity dto = noticeService.nout();
        model.addAttribute("dto",dto);
        return "notice/noticedetail";
    }
}
