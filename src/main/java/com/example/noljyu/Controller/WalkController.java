package com.example.noljyu.Controller;

import com.example.noljyu.DTO.WalkDTO;
import com.example.noljyu.DTO.WalkWriteDTO;
import com.example.noljyu.Entity.WalkEntity;
import com.example.noljyu.Entity.WalkWriteEntity;
import com.example.noljyu.Repository.WalkRepository;
import com.example.noljyu.Service.WalkService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
public class WalkController {
    @Autowired
    WalkService walkService;
    @Autowired
    WalkRepository walkRepository;
    String path = "C:\\MBC12AI\\springboot\\noljyu\\src\\main\\resources\\static\\image";

    @GetMapping("/walkinput")
    public String walkinput(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = authentication.getName();

        WalkDTO walkDTO = new WalkDTO();
        walkDTO.setId(loginId);  // 로그인한 아이디를 DTO에 세팅

        model.addAttribute("walkCourse", walkDTO); // 뷰에 전달
        return "walk/walkinput";
    }

    @PostMapping(value = "/walksave")
    public String walksave(WalkDTO dto, MultipartHttpServletRequest mul) throws IOException {
        MultipartFile mf = mul.getFile("walkimg");

        String fname = ""; // 기본 파일명 초기화
        if (mf != null && !mf.isEmpty()) {
            String originalName = mf.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            fname = uuid + "_" + originalName;
            mf.transferTo(new File(path + "\\" + fname));
            dto.setWalkimgName(fname);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = auth.getName(); // 로그인한 사용자 ID
        dto.setId(currentUserId);

        WalkEntity entity = dto.toentity(fname); // DTO → Entity 변환
        walkService.walkinput(entity);

        return "redirect:/";
    }

    @GetMapping("/walkout")
    public String walkout(Model mo,
                             @RequestParam(value = "keyword", required = false) String keyword) {
        List<WalkEntity> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = walkRepository.findByWalktitleContaining(keyword);
        } else {
            list = walkService.walkout(); // 전체 출력
        }
        mo.addAttribute("list", list);
        return "walk/walkout";
    }

    @GetMapping("/walkdetail")
    public String walkDetail(@RequestParam("walknum") Long walknum, Model mo) {
        WalkEntity dto = walkService.getWalkDetail(walknum);
        walkService.walkcnt(walknum);
        mo.addAttribute("dto", dto);
        return "walk/walkdetail";
    }

    @GetMapping("/walkupdate1")
    public String walkUpdate(@RequestParam("walknum") Long walknum, Model mo) {
        WalkEntity dto = walkService.getWalkDetail(walknum);
        mo.addAttribute("dto", dto);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = auth.getName();
        mo.addAttribute("id", currentUserId);

        return "walk/walkupdate";
    }

    @PostMapping(value = "/walkupdatesave")
    public String walkupdatesave(WalkDTO dto, MultipartHttpServletRequest mul) throws IOException {
        MultipartFile mf = mul.getFile("walkimg");

        String fname = ""; // 기본 파일명 초기화
        if (mf != null && !mf.isEmpty()) {
            String originalName = mf.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            fname = uuid + "_" + originalName;
            mf.transferTo(new File(path + "\\" + fname));
            dto.setWalkimgName(fname);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = auth.getName(); // 로그인한 사용자 ID
        dto.setId(currentUserId);

        WalkEntity entity = dto.toentity(fname); // DTO → Entity 변환
        walkService.walkupdate(entity);

        return "redirect:/walk/walkout";
    }

    @GetMapping(value = "/walkdelete1")
    public String walkdelete1(@RequestParam("walknum") Long walknum, Model mo) {
        WalkEntity wentity=walkService.walkdelete(walknum);
        // 이미지 경로 지정
        String fname = wentity.getWalkimg();
        File ff = new File(path + "\\" + fname);
        // 파일이 존재하면 삭제
        if (ff.exists()) {
            ff.delete();
        }

        return "redirect:/walkout";
    }

    @GetMapping("/walksearch")
    public String walksearch(@RequestParam("walkkey") String walkkey,
                             @RequestParam("walkvalue") String walkvalue,
                             Model model) {

        // 유효한 검색 키가 아니면 기본값 처리 (보안적 이유)
        if (!List.of("title", "content", "writer").contains(walkkey)) {
            walkkey = "title";
        }

        List<WalkDTO> list = walkService.walksearch(walkkey, walkvalue);

        model.addAttribute("list", list);
        model.addAttribute("walkvalue", walkvalue);

        return "walk/walksearch";
    }
    @GetMapping("walkwrite")
    public String walkwrite(){
        return "walk/walkwrite";
    }
    @PostMapping("/walkwritesave")
    public String wsave(WalkWriteDTO dto){
        WalkWriteEntity wwr = dto.walkwrite();
        walkService.wwr(wwr);
        return "redirect:/walkout";
    }
    @GetMapping("walklist")
    public String walklist(Model model){
        List<WalkWriteEntity> list = walkService.walklist();
        model.addAttribute("list",list);
        return "walk/walklist";
    }

}