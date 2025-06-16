package com.example.noljyu.Controller;

import com.example.noljyu.DTO.LetterDTO;
import com.example.noljyu.DTO.RememberDTO;
import com.example.noljyu.Entity.LetterEntity;
import com.example.noljyu.Entity.RememberEntity;
import com.example.noljyu.Service.RememberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class HealthController {

    @Autowired
    RememberService rememberService;
    String path="C:\\MBC12AI\\springboot\\noljyu\\src\\main\\resources\\static\\image";

    @GetMapping(value = "hospital")
    public String hospital(){
        return "map/hospital";
    }


    @GetMapping("/remember")
    public String remember(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        String loginId = userDetails.getUsername();
        RememberEntity dto = (RememberEntity) rememberService.rememberId(loginId);
        model.addAttribute("dto",dto);
        List<LetterEntity> list = rememberService.letter(loginId);
        model.addAttribute("list",list);
        return "map/remember";
    }

    @GetMapping("/letter")
    public String letter() {
        return "map/letter";
    }

    @PostMapping("/lettersave")
    public String lettersave(LetterDTO dto) {
            LetterEntity lentity = dto.lettersave();
            rememberService.lettersave(lentity);
            return "redirect:/remember";
    }

    @GetMapping("/letterdetail")
    public String letterdetail(Model model, @RequestParam("letternum") long letternum) {
        LetterEntity ldto = rememberService.letterdetail(letternum);
        model.addAttribute("ldto",ldto);
        return "map/letterdetail";
    }
    @GetMapping("/pet")
    public String pet(){
        return "map/pet";
    }
    @PostMapping("/petsave")
    public String petsave(MultipartHttpServletRequest request,
                          RememberDTO dto) throws IOException {
        MultipartFile mf = request.getFile("petimg");
        if (mf != null && !mf.isEmpty()) {
            UUID uu = UUID.randomUUID();
            String petimage = uu + "_" + mf.getOriginalFilename();
            mf.transferTo(new File(path + "//" + petimage));
            dto.setPetimg2(petimage);
        }
        else {
            dto.setPetimg2("default.jpg");
        }

        RememberEntity rent = dto.petsave();
        rememberService.petsave(rent);
        return "redirect:/remember";
    }


}

